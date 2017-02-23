package com.plucial.mulcms.sales.service.geo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.InvalidKeyException;

import org.slim3.util.StringUtil;

import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderLocationType;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.exception.AddressException;
import com.plucial.mulcms.sales.exception.GeocodeStatusException;
import com.plucial.mulcms.sales.exception.GeocoderLocationTypeException;

public class GeoService {

    private static final String GEOCODE_REQUEST_URL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=false";
    
    /**
     * 住所の位置情報を取得
     * @param environment
     * @param address
     * @param lang
     * @return
     * @throws GeocodeStatusException
     * @throws IOException
     * @throws GeocoderLocationTypeException
     * @throws InvalidKeyException
     * @throws AddressException 
     */
    public static GeoModel getGeoModel(
            Environment environment,
            String address) throws GeocodeStatusException, IOException, GeocoderLocationTypeException, InvalidKeyException, AddressException {
        
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        GeocodeResponse geocoderResponse = gson.fromJson(jsonCoord(environment, address), GeocodeResponse.class);
        
        // ステータスがOKではない場合エラーを生成
        if(geocoderResponse.getStatus() != GeocoderStatus.OK) {
            throw new GeocodeStatusException(geocoderResponse.getStatus());
        }
        
        // 不完全な住所の場合はエラー
        GeoModel model = new GeoModel(geocoderResponse);
        // 結果として取得した住所はトップレベルもしくは類似ではなければエラー
        if(model.getLocationType() != GeocoderLocationType.ROOFTOP && model.getLocationType() != GeocoderLocationType.APPROXIMATE) {
            throw new GeocoderLocationTypeException();
        }
        
        if(StringUtil.isEmpty(model.getPostalCodeLongName())) {
            throw new AddressException();
        }
        
        return model;
    }
    
    /**
     * APIの呼び出し
     * @param environment
     * @param address
     * @param lang
     * @return
     * @throws IOException
     */
    private static String jsonCoord(Environment environment, String address) throws IOException {
        URL url = new URL(GEOCODE_REQUEST_URL + "&address=" + URLEncoder.encode(address, "UTF-8") + "&language=en" + "&key=" + environment.getGoogleApiPublicServerKey());
        
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String inputLine;
        String jsonResult = "";
        while ((inputLine = in.readLine()) != null) {
            jsonResult += inputLine;
        }
        in.close();
        return jsonResult; 
    }

}
