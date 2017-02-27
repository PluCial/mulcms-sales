package com.plucial.mulcms.sales.service.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.db.StatisticsService;

public class InfoMailService extends EMailService {
    
    /**
     * パートナー コンタクトメール
     * @param environment
     * @param company
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public static void partnerCotactMail(
            Environment environment,
            Company company) throws UnsupportedEncodingException, MessagingException {
        
        // タイトル
        String subject = "初めまして";
        
        // メッセージ
        StringBuilder message = new StringBuilder();
        message.append("パートナー Infoメール");
        message.append("\n\n");
        message.append("以下の内容でお申し込みを承りました。");
        message.append("\n");
        message.append("◆ お申し込み内容");
        message.append("\n");
        message.append("-------------------------------------------------");
        message.append("\n");
        message.append("-------------------------------------------------");
        message.append("\n\n\n");
        message.append("◆ ご利用開始方法");
        message.append("\n");
        message.append("下記URLをクリックし登録を完了してください。");
        message.append("\n");
        message.append("\n\n");
        
        send(environment, company.getEmail().getEmail(), company.getName() + "様", App.PARTNAR_EMAIL_FROM_ADDRESS, App.PARTNER_MAIL_FROM_PERSONAL, subject, message.toString());
        
    }
    
    /**
     * クライアントコンタクトメール
     * @param localeProp
     * @param recipientAddress
     * @param registerUrl
     * @param environment
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public static void clientCotactMail(
            Environment environment,
            Company company) throws UnsupportedEncodingException, MessagingException {
        
        // タイトル
        String subject = "初めまして";
        
        // メッセージ
        StringBuilder message = new StringBuilder();
        message.append("クライアント Infoメール");
        message.append("\n\n");
        message.append("以下の内容でお申し込みを承りました。");
        message.append("\n");
        message.append("◆ お申し込み内容");
        message.append("\n");
        message.append("-------------------------------------------------");
        message.append("\n");
        message.append("-------------------------------------------------");
        message.append("\n\n\n");
        message.append("◆ ご利用開始方法");
        message.append("\n");
        message.append("下記URLをクリックし登録を完了してください。");
        message.append("\n");
        message.append("\n\n");
        
        send(environment, company.getEmail().getEmail(), company.getName()+ "様", App.CLIENT_EMAIL_FROM_ADDRESS, App.CLIENT_MAIL_FROM_PERSONAL, subject, message.toString());
        
    }
    
    /**
     * info メール送信成功
     * @param model
     */
    private static void infoMailSuccess(Company model, boolean isFromTask) {

        // 未配信数統計Modelの取得
        Statistics pendingDeliveryStatistics = null;
        Statistics deliveredStatistics = null;
        
        if(!isFromTask) {
            if(model instanceof Client) {
                pendingDeliveryStatistics = StatisticsService.getClientByStatus(ContactStatus.pending_delivery);
                deliveredStatistics = StatisticsService.getClientByStatus(ContactStatus.delivered);

            }else if(model instanceof Partner) {
                pendingDeliveryStatistics = StatisticsService.getPartnerByStatus(ContactStatus.pending_delivery);
                deliveredStatistics = StatisticsService.getPartnerByStatus(ContactStatus.delivered);

            }else {
                return;
            }
        }
        
        Transaction tx = Datastore.beginTransaction();
        try {
            model.setContactStatus(ContactStatus.delivered);
            Datastore.put(tx, model);
            
            if(!isFromTask) {
                // 未配信の減算
                pendingDeliveryStatistics.setStatistic(pendingDeliveryStatistics.getStatistic() - 1);
                Datastore.put(tx, pendingDeliveryStatistics);
                // 配信済数の加算
                deliveredStatistics.setStatistic(deliveredStatistics.getStatistic() + 1);
                Datastore.put(tx, deliveredStatistics);
            }

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
    private static void infoMailFailure(Company model, boolean isFromTask) {
        // 未配信数統計Modelの取得
        Statistics pendingDeliveryStatistics = null;
        Statistics deliveryFailureStatistics = null;
        
        if(!isFromTask) {
            if(model instanceof Client) {
                pendingDeliveryStatistics = StatisticsService.getClientByStatus(ContactStatus.pending_delivery);
                deliveryFailureStatistics = StatisticsService.getClientByStatus(ContactStatus.delivery_failure);

            }else if(model instanceof Partner) {
                pendingDeliveryStatistics = StatisticsService.getPartnerByStatus(ContactStatus.pending_delivery);
                deliveryFailureStatistics = StatisticsService.getPartnerByStatus(ContactStatus.delivery_failure);

            }else {
                return;
            }
        }
        
        Transaction tx = Datastore.beginTransaction();
        try {
            model.setContactStatus(ContactStatus.delivery_failure);
            Datastore.put(tx, model);
            
            if(!isFromTask) {
                // 未配信の減算
                pendingDeliveryStatistics.setStatistic(pendingDeliveryStatistics.getStatistic() - 1);
                Datastore.put(tx, pendingDeliveryStatistics);
                // 配信失敗数の加算
                deliveryFailureStatistics.setStatistic(deliveryFailureStatistics.getStatistic() + 1);
                Datastore.put(tx, deliveryFailureStatistics);
            }

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
    public static void sendInfoMail(Environment environment, Company model, boolean isFromTask) throws UnsupportedEncodingException {
        
        if(model.getEmail() == null || StringUtil.isEmpty(model.getEmail().getEmail())) {
            model.setEmail(new Email("info@" + model.getDomain()));
        }
        
        try {
            if(model instanceof Partner) {
                partnerCotactMail(environment, model);
                
            }else if(model instanceof Client) {
                clientCotactMail(environment, model);
                
            }else {
                return;
            }
            
//            throw new MessagingException();  //ローカルでの失敗テスト用
            infoMailSuccess(model, isFromTask);
            
        } catch (MessagingException e) {
            model.setEmail(null);
            infoMailFailure(model, isFromTask);
        }
    }

}
