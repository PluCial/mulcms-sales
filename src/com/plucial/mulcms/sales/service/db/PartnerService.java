package com.plucial.mulcms.sales.service.db;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.sales.dao.PartnerDao;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.LocationCode;
import com.plucial.mulcms.sales.exception.AddressException;
import com.plucial.mulcms.sales.exception.GeocodeStatusException;
import com.plucial.mulcms.sales.exception.GeocoderLocationTypeException;
import com.plucial.mulcms.sales.exception.ObjectNotExistException;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.geo.GeoModel;
import com.plucial.mulcms.sales.service.geo.GeoService;
import com.plucial.mulcms.sales.service.search.PartnerSearchService;


public class PartnerService extends CompanyService {
    
    /** DAO */
    private static final PartnerDao dao = new PartnerDao();
    
    /**
     * PUT
     * <pre>
     * タスクでまとめて追加する場合は、複数のスレッドから同じStatistics エンティティに対してGET、PUTを
     * 行うため、ConcurrentModificationException が発生する.
     * そのために、このメソッドでは 「会社総数の加算」と「未配信数の加算」を行わない。
     * 
     * GCPの管理画面から手動でStatistics のカウンターを修正する。
     * カウンター数は GCPのインデックスから確認できる。
     * </pre>
     * @param name
     * @param homepage
     * @param address
     * @param email
     * @param phoneNumber
     * @param responsiblePartyName
     * @return
     * @throws MalformedURLException 
     */
    public static Partner put(String name, String homepage, String address, String email, String phoneNumber, String responsiblePartyName, boolean isFromTask) throws MalformedURLException  {
        Partner model = null;
        String companyDomain = getCompanyDomainByHomePage(homepage);
        
        try {
            model = (Partner) getByDomain(companyDomain);
            return model;

        } catch (ObjectNotExistException e) {

            // 新規作成
            Transaction tx = Datastore.beginTransaction();
            try {
                model = new Partner();


                model.setKey(createKey());
                model.setDomain(StringUtil.isEmpty(companyDomain) ? null : companyDomain);
                model.setName(StringUtil.isEmpty(name) ? null : name.trim());
                model.setHomepage(StringUtil.isEmpty(homepage) ? null : homepage.trim());
                model.setAddress(StringUtil.isEmpty(address) ? null : address.trim());
                
                model.setEmail(StringUtil.isEmpty(email) ? null : new Email(email.trim()));
                model.setPhoneNumber(StringUtil.isEmpty(phoneNumber) ? null : new PhoneNumber(phoneNumber.trim()));
                model.setResponsiblePartyName(StringUtil.isEmpty(responsiblePartyName) ? null : responsiblePartyName.trim());

                Datastore.put(tx, model);

                // Taskからではない場合、カウントを更新
                if(!isFromTask) {
                    // 会社総数の加算
                    Statistics statistics = StatisticsService.getNumber(StatisticsService.PARTNER_TOTAL_NUMBER_KEY);
                    statistics.setStatistic(statistics.getStatistic() + 1);
                    StatisticsService.put(tx, statistics);

                    // 未配信数の加算
                    Statistics pendingDeliveryStatistics = StatisticsService.getPartnerByStatus(ContactStatus.pending_delivery);
                    pendingDeliveryStatistics.setStatistic(pendingDeliveryStatistics.getStatistic() + 1);
                    StatisticsService.put(tx, pendingDeliveryStatistics);
                }
                
                // 検索対象にする
                PartnerSearchService.put(model);

                tx.commit();

            }finally {
                if(tx.isActive()) {
                    tx.rollback();
                }
            }
        }
        
        return model;
    }
    
    /**
     * PUT
     * <pre>
     * タスクでまとめて追加する場合は、複数のスレッドから同じStatistics エンティティに対してGET、PUTを
     * 行うため、ConcurrentModificationException が発生する.
     * そのために、このメソッドでは 「会社総数の加算」と「未配信数の加算」を行わない。
     * 
     * GCPの管理画面から手動でStatistics のカウンターを修正する。
     * カウンター数は GCPのインデックスから確認できる。
     * </pre>
     * @param name
     * @param homepage
     * @param address
     * @return
     * @throws MalformedURLException 
     */
    public static Partner put(String name, String homepage, String address, boolean isFromTask) throws MalformedURLException {
        return put(name, homepage, address, null, null, null, isFromTask);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void put(Partner model) {
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, model);
            
            // 検索対象を更新
            PartnerSearchService.put(model);
            
            tx.commit();
            
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * 正式パートナーに変更
     * @param model
     * @throws AddressException 
     * @throws GeocoderLocationTypeException 
     * @throws IOException 
     * @throws GeocodeStatusException 
     * @throws InvalidKeyException 
     */
    public static void changePartnerFlag(Partner model) throws InvalidKeyException, GeocodeStatusException, IOException, GeocoderLocationTypeException, AddressException {
        
        if(StringUtil.isEmpty(model.getAddress())) {
            throw new AddressException();
        }

        // パートナー統計Model
        Statistics partnerStatistics = StatisticsService.getNumber(StatisticsService.PARTNER_NUMBER_KEY);


        Transaction tx = Datastore.beginTransaction();
        try {
            if(!model.isPartner()) {
                // 加算
                partnerStatistics.setStatistic(partnerStatistics.getStatistic() + 1);
                model.setPartner(true);
                
                // 住所情報の取得
                if((model.getLocationCode() == null || model.getGeoModel() == null)) {
                    GeoModel geoModel = GeoService.getGeoModel(Environment.production, model.getAddress());
                    model.setGeoModel(geoModel);
                    model.setLocationCode(getLocationCode(geoModel));
                }
                
            }else {
                // 減算
                partnerStatistics.setStatistic(partnerStatistics.getStatistic() - 1);
                model.setPartner(false);
            }
            
            // 保存
            StatisticsService.put(tx, partnerStatistics);
            Datastore.put(tx, model);

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        } 
    }
    
    /**
     * リストの取得
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Partner> getPartnerList(int limit, String cursor) {
        return dao.getPartnerList(limit, cursor);
    }
    
    /**
     * リストの取得
     * @param status
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Partner> getList(ContactStatus status, int limit, String cursor) {
        return dao.getList(status, limit, cursor);
    }
    
    /**
     * 地域検索
     * @param locationCode
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Partner> searchPartnerByLocationCode(LocationCode locationCode, int limit, String cursor) {
        return dao.searchPartnerByLocationCode(locationCode, limit, cursor);
    }

}
