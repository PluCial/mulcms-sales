package com.plucial.mulcms.sales.service.db;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.sales.dao.StatisticsDao;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.ProjectStatus;
import com.plucial.mulcms.sales.exception.ObjectNotExistException;
import com.plucial.mulcms.sales.meta.StatisticsMeta;
import com.plucial.mulcms.sales.model.Statistics;


public class StatisticsService {
    
    /** DAO */
    private static final StatisticsDao dao = new StatisticsDao();
    
    /**
     * 会社総数(クライアント)
     */
    public static final String CLIENT_TOTAL_NUMBER_KEY = "client_target_total_number";
    
    /**
     * 会社総数(パートナー)
     */
    public static final String PARTNER_TOTAL_NUMBER_KEY = "partner_target_total_number";
    
    /**
     * パートナー数
     */
    public static final String PARTNER_NUMBER_KEY = "partner_number";
    
    /**
     * プロジェクト総数
     */
    public static final String PROJECT_TOTAL_NUMBER_KEY = "project_total_number";
    
    /**
     * クライアント総数の取得
     * @return
     * @throws ObjectNotExistException 
     */
    public static Statistics getNumber(String key) {
        
        Statistics model = dao.getOrNull(createKey(key));
        if(model == null) {
            model = new Statistics();
            model.setKey(createKey(key));
            Datastore.put(model);
        }
        
        return model;
    }
    
    /**
     * 各ステータスの総数の取得
     * @return
     * @throws ObjectNotExistException 
     */
    public static Statistics getClientByStatus(ContactStatus status) {
        return getNumber("client_contact_" + status.toString());
    }
    
    /**
     * 各ステータスの総数の取得
     * @return
     * @throws ObjectNotExistException 
     */
    public static Statistics getPartnerByStatus(ContactStatus status) {
        return getNumber("partner_contact_" + status.toString());
    }
    
    /**
     * 各ステータスの総数の取得
     * @return
     * @throws ObjectNotExistException 
     */
    public static Statistics getProjectByStatus(ProjectStatus status) {
        return getNumber("project_contact_" + status.toString());
    }
    
    /**
     * 更新
     * @param tx
     * @param model
     */
    public static void put(Transaction tx, Statistics model) {
        Datastore.put(tx, model);
    }
    
    
    /**
     * 更新
     * @param model
     */
    public static void put(Statistics model) {
        Datastore.put(model);
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
        return Datastore.createKey(StatisticsMeta.get(), keyString);
    }

}
