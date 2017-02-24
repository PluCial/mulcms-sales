package com.plucial.mulcms.sales.service.collect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.plucial.mulcms.sales.service.db.PartnerService;

/**
 * 収集先
 * https://imitsu.jp/list/hp-design/
 * @author takahara
 *
 */
public class PartnerCollectService {

    /**
     * メインメソッド
     * @throws IOException 
     */
    public static void Build(String urlStr, boolean isFromTask) throws IOException {
        
        Document companyListDocument = getDocument(urlStr);
        Elements serviceItemElems = companyListDocument.select("#services-contents .item.recommend");
        
        for (Element serviceItemElem : serviceItemElems) {
            
            try {
                // 会社名
                Element companyNameElem = serviceItemElem.select(".item-header .name .primary a").get(0);
                String companyName = companyNameElem.text().trim();


                // ホームページリンク
                Element homePageUrlElem = serviceItemElem.select(".item-body a.view_homepage").get(0);
                String homePageUrl = homePageUrlElem.attr("href").trim();

                // サービスURL
                Element serviceUrl = serviceItemElem.select(".item-header .name .secondary a").get(0);
                String companyServicePageUrl = "https://imitsu.jp" +  serviceUrl.attr("href").trim();
                System.out.println(companyServicePageUrl);

                // サービスページ取得
                Document companyServiceDocument = getDocument(companyServicePageUrl);


                // 住所の取得
                Elements addressElems = companyServiceDocument.select("[name=company_address]");
                String address = addressElems.get(0).attr("value").trim();

                PartnerService.put(companyName, homePageUrl, address, isFromTask);
            }catch(Exception e) {
                // エラーの場合は取り込まない
                e.printStackTrace();
            }
            
        }
    }
    
    /**
     * HTML 取得
     * @param urlStr
     * @return
     * @throws IOException 
     */
    private static Document getDocument(String urlStr) throws IOException {
        
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setReadTimeout(60000); // 60秒
        connection.setConnectTimeout(60000); // 60秒
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestMethod("GET");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
        StringBuffer resposeHtml = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            resposeHtml.append(line);
        }
        reader.close();
        
//        InputStream is = new ByteArrayInputStream(resposeHtml.toString().getBytes());
//        return Jsoup.parse(is, "UTF-8", "");
        return Jsoup.parse(resposeHtml.toString());
//        return Jsoup.parse(new String(resposeHtml.toString().getBytes(), "UTF-8"));
    }
}
