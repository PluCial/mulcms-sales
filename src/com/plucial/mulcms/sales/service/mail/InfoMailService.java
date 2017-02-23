package com.plucial.mulcms.sales.service.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.db.StatisticsService;

public class InfoMailService extends EMailService {
    
    /**
     * info メール送信成功
     * @param model
     */
    private static void infoMailSuccess(Company model) {

        // 未配信数統計Modelの取得
        Statistics pendingDeliveryStatistics = null;
        Statistics deliveredStatistics = null;
        
        if(model instanceof Client) {
            pendingDeliveryStatistics = StatisticsService.getClientStatus(ContactStatus.pending_delivery);
            deliveredStatistics = StatisticsService.getClientStatus(ContactStatus.delivered);
        
        }else if(model instanceof Partner) {
            pendingDeliveryStatistics = StatisticsService.getPartnerStatus(ContactStatus.pending_delivery);
            deliveredStatistics = StatisticsService.getPartnerStatus(ContactStatus.delivered);
            
        }else {
            return;
        }
        
        Transaction tx = Datastore.beginTransaction();
        try {
            model.setContactStatus(ContactStatus.delivered);
            Datastore.put(tx, model);
            
            // 未配信の減算
            pendingDeliveryStatistics.setStatistic(pendingDeliveryStatistics.getStatistic() - 1);
            Datastore.put(tx, pendingDeliveryStatistics);
            // 配信済数の加算
            deliveredStatistics.setStatistic(deliveredStatistics.getStatistic() + 1);
            Datastore.put(tx, deliveredStatistics);

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * info メール送信失敗
     * @param model
     */
    private static void infoMailFailure(Company model) {
        // 未配信数統計Modelの取得
        Statistics pendingDeliveryStatistics = null;
        Statistics deliveryFailureStatistics = null;
        
        if(model instanceof Client) {
            pendingDeliveryStatistics = StatisticsService.getClientStatus(ContactStatus.pending_delivery);
            deliveryFailureStatistics = StatisticsService.getClientStatus(ContactStatus.delivery_failure);
        
        }else if(model instanceof Partner) {
            pendingDeliveryStatistics = StatisticsService.getPartnerStatus(ContactStatus.pending_delivery);
            deliveryFailureStatistics = StatisticsService.getPartnerStatus(ContactStatus.delivery_failure);
            
        }else {
            return;
        }
        
        Transaction tx = Datastore.beginTransaction();
        try {
            model.setContactStatus(ContactStatus.delivery_failure);
            Datastore.put(tx, model);
            
            // 未配信の減算
            pendingDeliveryStatistics.setStatistic(pendingDeliveryStatistics.getStatistic() - 1);
            Datastore.put(tx, pendingDeliveryStatistics);
            // 配信失敗数の加算
            deliveryFailureStatistics.setStatistic(deliveryFailureStatistics.getStatistic() + 1);
            Datastore.put(tx, deliveryFailureStatistics);

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * Info メールの送信
     * @param model
     * @throws UnsupportedEncodingException 
     */
    public static void sendInfoMail(Environment environment, Company model) throws UnsupportedEncodingException {
        
        model.setEmail(new Email("info@" + model.getDomain()));
        
        try {
            if(model instanceof Partner) {
                partnerCotactMail(environment, model);
                
            }else if(model instanceof Client) {
                clientCotactMail(environment, model);
                
            }else {
                return;
            }
            
//            throw new MessagingException();  //ローカルでの失敗テスト用
            infoMailSuccess(model);
            
        } catch (MessagingException e) {
            model.setEmail(null);
            infoMailFailure(model);
        }
    }

}
