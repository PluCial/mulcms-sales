package com.plucial.mulcms.sales.service.db;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.plucial.mulcms.sales.dao.MailTemplateDao;
import com.plucial.mulcms.sales.enums.MailKey;
import com.plucial.mulcms.sales.meta.MailTemplateMeta;
import com.plucial.mulcms.sales.model.MailTemplate;


public class MailTemplateService {
    
    /** DAO */
    private static final MailTemplateDao dao = new MailTemplateDao();
    
    /**
     * 取得
     * @param key
     * @return
     */
    public static MailTemplate get(MailKey key) {
        MailTemplate model = dao.getOrNull(createKey(key));
        if(model == null) {
            model = new MailTemplate();
            model.setKey(createKey(key));
            Datastore.put(model);
        }
        
        return model;
    }
    
    /**
     * PUT
     * @param model
     */
    public static void put(MailTemplate model) {
        dao.put(model);
    }
    
    /**
     * PUT
     * @param key
     * @param fromPersonal
     * @param subject
     * @param message
     */
    public static void put(MailKey key, String fromPersonal, String subject, String message) {
        MailTemplate model = get(key);
        
        model.setFromPersonal(fromPersonal);
        model.setSubject(subject);
        model.setMessage(new Text(message));
        
        dao.put(model);
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(MailKey key) {
        return Datastore.createKey(MailTemplateMeta.get(), key.toString());
    }

}
