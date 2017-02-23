package com.plucial.mulcms.sales.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.mulcms.sales.meta.CompanyMeta;
import com.plucial.mulcms.sales.model.Company;

public class CompanyDao extends DaoBase<Company>{

    /** META */
    private static final CompanyMeta meta =  CompanyMeta.get();
    
    /**
     * リストの取得
     * @param attribute
     * @return
     */
    public Company getByDomain(String domain) {
        List<Company> list =  Datastore.query(meta)
                .filter(meta.domain.equal(domain))
                .asQueryResultList();
        
        if(list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
}
