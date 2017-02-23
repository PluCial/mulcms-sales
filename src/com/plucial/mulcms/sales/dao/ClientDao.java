package com.plucial.mulcms.sales.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.datastore.Sort;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.enums.ClientIndustry;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.meta.ClientMeta;
import com.plucial.mulcms.sales.model.Client;

public class ClientDao extends DaoBase<Client>{
    
    /** META */
    private static final ClientMeta meta =  ClientMeta.get();
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    private S3QueryResultList<Client> getList(int limit) {
        return Datastore.query(meta)
                .sort(new Sort(meta.createDate))
                .limit(limit)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Client> getList(int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(limit);
        return Datastore.query(meta)
                .sort(new Sort(meta.createDate))
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Client> getList(ContactStatus status, int limit) {
        return Datastore.query(meta)
            .filter(meta.contactStatus.equal(status))
            .sort(new Sort(meta.createDate))
            .limit(limit)
            .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Client> getList(ContactStatus status, int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(status, limit);
        
        return Datastore.query(meta)
                .filter(meta.contactStatus.equal(status))
                .sort(new Sort(meta.createDate))
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    private S3QueryResultList<Client> getList(ClientIndustry clientIndustry, int limit) {
        return Datastore.query(meta)
                .filter(meta.industry.equal(clientIndustry))
                .sort(new Sort(meta.createDate))
                .limit(limit)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Client> getList(ClientIndustry clientIndustry, int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(clientIndustry, limit);
        
        return Datastore.query(meta)
                .filter(meta.industry.equal(clientIndustry))
                .sort(new Sort(meta.createDate))
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Client> getList(ClientIndustry clientIndustry, ContactStatus status, int limit) {
        return Datastore.query(meta)
            .filter(
                meta.industry.equal(clientIndustry),
                meta.contactStatus.equal(status)
                )
            .sort(new Sort(meta.createDate))
            .limit(limit)
            .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public S3QueryResultList<Client> getList(ClientIndustry clientIndustry, ContactStatus status, int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(clientIndustry, status, limit);
        
        return Datastore.query(meta)
                .filter(
                    meta.industry.equal(clientIndustry),
                    meta.contactStatus.equal(status))
                .sort(new Sort(meta.createDate))
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }

}
