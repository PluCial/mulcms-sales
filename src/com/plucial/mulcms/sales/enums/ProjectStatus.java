package com.plucial.mulcms.sales.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum ProjectStatus {
    new_project("新規", "fa fa-circle-o"),
    in_progress("進行中", "fa fa-dot-circle-o"),
    on_hold("保留", "fa fa-minus-square-o"),
    ordered("受注", "fa fa-check-square-o"),
    loss_of_order("失注", "fa fa-minus-square");
    
    
    /** 名前 */
    private String name;
    
    private String iconClass;
    
    /**
     * コンストラクター
     * @param attr
     */
    private ProjectStatus(String name, String iconClass) {
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
