package com.plucial.mulcms.sales.enums;

import com.plucial.mulcms.sales.App;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum MailKey {
    partner_contact_mail("コンタクトメール (パートナー)", App.PARTNAR_EMAIL_FROM_ADDRESS),
    client_contact_mail("コンタクトメール (クライアント)", App.CLIENT_EMAIL_FROM_ADDRESS);
    
    
    /** 名前 */
    private String name;
    
    private String fromAddress;
    
    /**
     * コンストラクター
     * @param attr
     */
    private MailKey(String name, String fromAddress) {
        this.setName(name);
        this.setFromAddress(fromAddress);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
}
