package com.plucial.mulcms.sales.service.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.plucial.mulcms.sales.enums.Environment;


public class EMailService {
    
    
    
    /**
     * メール送信
     * @param environment
     * @param sendAddress
     * @param sendPersonal
     * @param fromAddress
     * @param fromPersonal
     * @param subject
     * @param message
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public static void send(
            Environment environment,
            String sendAddress,
            String sendPersonal,
            String fromAddress,
            String fromPersonal,
            String subject, 
            String message) throws UnsupportedEncodingException, MessagingException {
        
        if(environment == Environment.Local) {
            System.out.println("sendAddress:" + sendAddress);
            System.out.println("sendPersonal:" + sendPersonal);
            System.out.println("fromAddress:" + fromAddress);
            System.out.println("fromPersonal:" + fromPersonal);
            System.out.println("subject:" + subject);
            System.out.println("message:");
            System.out.println(message.toString());
            return;
        }
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);

        //発信元情報：メールアドレス、名前
        msg.setFrom(new InternetAddress(fromAddress, fromPersonal, "ISO-2022-JP"));

        //送信先情報
        msg.addRecipient(Message.RecipientType.TO,
            new InternetAddress(sendAddress, sendPersonal, "ISO-2022-JP"));

        msg.setSubject(subject, "ISO-2022-JP");
        msg.setText(message);

        Transport.send(msg);
        
    }

}
