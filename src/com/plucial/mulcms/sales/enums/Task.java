package com.plucial.mulcms.sales.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum Task {
    partner_collection("パートナー収集", "fa-cloud-download"),
    partner_contact_mail("パートナーコンタクトメール", "fa-envelope"),
    client_contact_mail("クライアントコンタクトメール", "fa-envelope-o"),
    refresh_statistics("リフレッシュカウンター", "fa-refresh");
    
    /** 名前 */
    private String name;
    
    private String iconClass;
    
    /**
     * コンストラクター
     * @param attr
     */
    private Task(String name, String iconClass) {
        this.setName(name);
        this.setIconClass(iconClass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
}
