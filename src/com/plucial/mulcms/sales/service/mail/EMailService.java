package com.plucial.mulcms.sales.service.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Company;


public class EMailService {
    
    /**
     * 登録メール
     * @param localeProp
     * @param recipientAddress
     * @param registerUrl
     * @param environment
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
        
        send(environment, company.getEmail().getEmail(), App.PARTNAR_EMAIL_FROM_ADDRESS, subject, message.toString());
        
    }
    
    /**
     * 登録メール
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
        
        send(environment, company.getEmail().getEmail(), App.PARTNAR_EMAIL_FROM_ADDRESS, subject, message.toString());
        
    }
    
    /**
     * メール送信
     * @param recipientAddress
     * @param subject
     * @param message
     * @param environment
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    private static void send(
            Environment environment,
            String recipientAddress, 
            String fromAddress,
            String subject, 
            String message) throws UnsupportedEncodingException, MessagingException {
        
        if(environment == Environment.Local) {
            System.out.println(subject);
            System.out.println(message.toString());
            return;
        }
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);

        //発信元情報：メールアドレス、名前
        msg.setFrom(new InternetAddress(fromAddress, App.CLIENT_EMAIL_FROM_ADDRESS, "ISO-2022-JP"));

        //送信先情報
        msg.addRecipient(Message.RecipientType.TO,
            new InternetAddress(recipientAddress));

        msg.setSubject(subject, "ISO-2022-JP");
        msg.setText(message);

        Transport.send(msg);
        
    }

}
