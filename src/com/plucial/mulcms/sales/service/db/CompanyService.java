package com.plucial.mulcms.sales.service.db;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.sales.dao.CompanyDao;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.LocationCode;
import com.plucial.mulcms.sales.exception.ObjectNotExistException;
import com.plucial.mulcms.sales.meta.CompanyMeta;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.geo.GeoModel;


public class CompanyService {
    
    /** DAO */
    private static final CompanyDao dao = new CompanyDao();
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException
     */
    public static Company get(String keyString) throws ObjectNotExistException {
        Company model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * ドメインから取得
     * @param domain
     * @return
     * @throws ObjectNotExistException
     */
    public static Company getByDomain(String domain) throws ObjectNotExistException {
        Company model = dao.getByDomain(domain);
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * コンタクトステータスの更新
     * @param model
     * @param newStatus
     */
    public static void changeStatus(Company model, ContactStatus newStatus) {
        
        if(model.getContactStatus() == newStatus) return;
        
        // 元の統計Model
        Statistics oldStatistics = null;
        // 新しい統計Model
        Statistics newStatistics = null;
        
        if(model instanceof Client) {
            oldStatistics = StatisticsService.getClientByStatus(model.getContactStatus());
            newStatistics = StatisticsService.getClientByStatus(newStatus);
        
        }else if(model instanceof Partner) {
            oldStatistics = StatisticsService.getPartnerByStatus(model.getContactStatus());
            newStatistics = StatisticsService.getPartnerByStatus(newStatus);
            
        }else {
            return;
        }

        
        Transaction tx = Datastore.beginTransaction();
        try {
            // 減算
            oldStatistics.setStatistic(oldStatistics.getStatistic() - 1);
            StatisticsService.put(tx, oldStatistics);
            // 加算
            newStatistics.setStatistic(newStatistics.getStatistic() + 1);
            StatisticsService.put(tx, newStatistics);
            
            // ステータス変更
            model.setContactStatus(newStatus);
            Datastore.put(tx, model);
            
            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * HP URL からドメインを取得
     * @param homePageUrl
     * @return
     * @throws MalformedURLException 
     */
    protected static String getCompanyDomainByHomePage(String homepage) throws MalformedURLException {
        URL url = new URL(homepage);
        return url.getHost().replace("www.", "");
    }
    
    /**
     * HP URL からドメインを取得
     * @param homePageUrl
     * @return
     * @throws MalformedURLException 
     */
    protected static String getCompanyDomainByEmail(String email) {
        
        String[] str = email.split("@");
        return str[1];
    }
    
    /**
     * 都道府県コードの取得
     * @param geoModel
     * @return
     */
    public static LocationCode getLocationCode(GeoModel geoModel) {
        for(LocationCode locationCode: LocationCode.values()) {
            for(String regionNumber: locationCode.getRegionNumberList()) {
                
                if(geoModel.getPostalCodeLongName().startsWith(regionNumber)) {
                    return locationCode;
                }
            }
        }
        
        return null;
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString) {
        return Datastore.createKey(CompanyMeta.get(), keyString);
    }
    
    /**
     * キーの作成
     * @return
     */
    public static Key createKey() {
        // キーを乱数にする
        UUID uniqueKey = UUID.randomUUID();
        return Datastore.createKey(Company.class, uniqueKey.toString());
    }
}
