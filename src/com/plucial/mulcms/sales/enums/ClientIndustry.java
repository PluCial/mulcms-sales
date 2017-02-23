package com.plucial.mulcms.sales.enums;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum ClientIndustry {
    hotel("ホテル"),
    etc("その他");
    
    /** 名前 */
    private String name;
    
    /**
     * コンストラクター
     * @param attr
     */
    private ClientIndustry(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
