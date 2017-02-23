package com.plucial.mulcms.sales.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum ContactStatus {
    pending_delivery("コンタクト待ち", "fa fa-paper-plane-o"),
    delivered("コンタクト完了", "fa fa-paper-plane"),
    delivery_failure("コンタクト失敗", "fa fa-times");
//    corresponding("対応予定", "fa fa-envelope-o"),
//    corresponding_to("対応済", "fa fa-envelope");
    
    
    /** 名前 */
    private String name;
    
    private String iconClass;
    
    /**
     * コンストラクター
     * @param attr
     */
    private ContactStatus(String name, String iconClass) {
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
