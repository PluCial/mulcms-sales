package com.plucial.mulcms.sales.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.enums.ProjectStatus;
import com.plucial.mulcms.sales.meta.ProjectMeta;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Project;

public class ProjectDao extends DaoBase<Project>{
    
    /** META */
    private static final ProjectMeta meta =  ProjectMeta.get();
    
    /**
     * リストの取得
     * @param status
     * @param limit
     * @return
     */
    private S3QueryResultList<Project> getList(ProjectStatus status, int limit) {
        return Datastore.query(meta)
                .filter(meta.status.equal(status))
                .sort(meta.createDate.desc)
                .limit(limit)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param status
     * @param limit
     * @param cursor
     * @return
     */
    public S3QueryResultList<Project> getList(ProjectStatus status, int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(status, limit);
        
        return Datastore.query(meta)
                .filter(meta.status.equal(status))
                .sort(meta.createDate.desc)
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param partner
     * @param limit
     * @return
     */
    private S3QueryResultList<Project> getList(Partner partner, int limit) {
        return Datastore.query(meta)
                .filter(meta.partnerRef.equal(partner.getKey()))
                .sort(meta.createDate.desc)
                .limit(limit)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param partner
     * @param limit
     * @param cursor
     * @return
     */
    public S3QueryResultList<Project> getList(Partner partner, int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(partner, limit);
        
        return Datastore.query(meta)
                .filter(meta.partnerRef.equal(partner.getKey()))
                .sort(meta.createDate.desc)
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param client
     * @param limit
     * @return
     */
    private S3QueryResultList<Project> getList(Client client, int limit) {
        return Datastore.query(meta)
                .filter(meta.clientRef.equal(client.getKey()))
                .sort(meta.createDate.desc)
                .limit(limit)
                .asQueryResultList();
    }
    
    /**
     * リストの取得
     * @param client
     * @param limit
     * @param cursor
     * @return
     */
    public S3QueryResultList<Project> getList(Client client, int limit, String cursor) {
        if (StringUtil.isEmpty(cursor)) return getList(client, limit);
        
        return Datastore.query(meta)
                .filter(meta.clientRef.equal(client.getKey()))
                .sort(meta.createDate.desc)
                .limit(limit)
                .encodedStartCursor(cursor)
                .asQueryResultList();
    }

}
