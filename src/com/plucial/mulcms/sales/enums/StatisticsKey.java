package com.plucial.mulcms.sales.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum StatisticsKey {
    client_target_total_number("会社総数(クライアント)"),
    client_contact_pending_delivery("コンタクト待ち(クライアント)"),
    client_contact_delivered("コンタクト完了(クライアント)"),
    client_contact_delivery_failure("コンタクト失敗(クライアント)"),
    partner_target_total_number("会社総数(パートナー)"),
    partner_contact_pending_delivery("コンタクト待ち(パートナー)"),
    partner_contact_delivered("コンタクト完了(パートナー)"),
    partner_contact_delivery_failure("コンタクト失敗(パートナー)");
    
    
    /** 名前 */
    private String name;
    
    /**
     * コンストラクター
     * @param attr
     */
    private StatisticsKey(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
