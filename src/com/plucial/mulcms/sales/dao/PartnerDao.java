package com.plucial.mulcms.sales.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.LocationCode;
import com.plucial.mulcms.sales.meta.PartnerMeta;
import com.plucial.mulcms.sales.model.Partner;

public class PartnerDao extends DaoBase<Partner>{
    
    /** META */
    private static final PartnerMeta meta =  PartnerMeta.get();
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    private S3QueryResultList<Partner> getPartnerList(int limit) {
        return Datastore.query(meta)
                .filter(meta.partner.equal(true))
                .sort(meta.numberOfProjects.desc, meta.createDate.asc)
                .limit(limit)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Partner> getPartnerList(int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getPartnerList(limit);
        
        return Datastore.query(meta)
                .filter(meta.partner.equal(true))
                .sort(meta.numberOfProjects.desc, meta.createDate.asc)
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Partner> getList(ContactStatus status, int limit) {
        return Datastore.query(meta)
            .filter(meta.contactStatus.equal(status))
            .sort(meta.numberOfProjects.desc, meta.createDate.asc)
            .limit(limit)
            .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Partner> getList(ContactStatus status, int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(status, limit);
        
        return Datastore.query(meta)
                .filter(meta.contactStatus.equal(status))
                .sort(meta.numberOfProjects.desc, meta.createDate.asc)
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * 地域毎パートナー検索
     * @param isClient
     * @param locationCode
     * @param status
     * @param limit
     * @return
     */
    public S3QueryResultList<Partner> searchPartnerByLocationCode(LocationCode locationCode, int limit) {
        return Datastore.query(meta)
                .filter(meta.partner.equal(true),
                    meta.locationCode.equal(locationCode))
                .sort(meta.numberOfProjects.desc, meta.createDate.asc)
                .limit(limit)
                .asQueryResultList();
    }
    
    /**
     * 地域毎パートナー検索
     * @param isPartner
     * @param locationCode
     * @param status
     * @param limit
     * @param cursor
     * @return
     */
    public S3QueryResultList<Partner> searchPartnerByLocationCode(LocationCode locationCode, int limit, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return searchPartnerByLocationCode(locationCode, limit);
        
        return Datastore.query(meta)
                .filter(meta.partner.equal(true),
                    meta.locationCode.equal(locationCode))
                .sort(meta.numberOfProjects.desc, meta.createDate.asc)
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * カウントの取得
     * @return
     */
    public int countAll() {
        return Datastore.query(meta).count();
    }
    
    /**
     * ステータス毎のカウント
     * @param status
     * @return
     */
    public int countStatus(ContactStatus status) {
        return Datastore.query(meta)
                .filter(meta.contactStatus.equal(status)).count();
    }


}
