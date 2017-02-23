package com.plucial.mulcms.sales.enums;

import java.util.Arrays;
import java.util.List;

/**
 * テキストリソース役割
 * <pre>
 * 追加や変更の場合は必ずTextResourcesService 内のUpdateも合わせて修正
 * </pre>
 * @author takahara
 *
 */
public enum LocationCode {
    // 北海道
    HK("北海道", Arrays.asList("00", "04", "05", "06", "07", "08", "09")),
    AK("秋田県", Arrays.asList("01")),
    IT("岩手県", Arrays.asList("02")),
    AO("青森県", Arrays.asList("03")),
    FS("福島県", Arrays.asList("96", "97")),
    MG("宮城県", Arrays.asList("98")),
    YG("山形県", Arrays.asList("99")),
    
    // 関東地方
    TY("東京都", Arrays.asList("10","11","12","13","14","15","16","17","18","19","20")),
    KN("神奈川県", Arrays.asList("21","22","23","24","25")),
    CB("千葉県", Arrays.asList("26","27","28","29")),
    IB("茨城県", Arrays.asList("30","31")),
    TC("栃木県", Arrays.asList("32")),
    ST("埼玉県", Arrays.asList("33","34","35","36")),
    GU("群馬県", Arrays.asList("37")),
    
    // 北陸地方
    FI("福井県", Arrays.asList("91")),
    IS("石川県", Arrays.asList("92")),
    TM("富山県", Arrays.asList("93")),
    NI("新潟県", Arrays.asList("94", "95")),
    
    // 甲信・東海地方
    NA("長野県", Arrays.asList("38", "39")),
    YN("山梨県", Arrays.asList("40")),
    SZ("静岡県", Arrays.asList("41", "42", "43")),
    AI("愛知県", Arrays.asList("44", "45", "46", "47", "48", "49")),
    GI("岐阜県", Arrays.asList("50")),
    ME("三重県", Arrays.asList("51")),
    
    // 近畿地方
    SI("滋賀県", Arrays.asList("52")),
    OS("大阪府", Arrays.asList("53", "54", "55", "56", "57", "58", "59")),
    KY("京都府", Arrays.asList("60", "61", "62")),
    NR("奈良県", Arrays.asList("63")),
    WA("和歌山県", Arrays.asList("64")),
    HG("兵庫県", Arrays.asList("65", "66", "67")),
    
    // 中国地方
    TT("鳥取県", Arrays.asList("68")),
    SM("島根県", Arrays.asList("69")),
    OY("岡山県", Arrays.asList("70", "71")),
    HS("広島県", Arrays.asList("72", "73")),
    YA("山口県", Arrays.asList("74", "75")),
    
    // 四国地方
    KA("香川県", Arrays.asList("76")),
    TK("徳島県", Arrays.asList("77")),
    KO("高知県", Arrays.asList("78")),
    EH("愛媛県", Arrays.asList("79")),
    
    // 九州・沖縄地方
    FO("福岡県", Arrays.asList("80", "81", "82", "83")),
    SG("佐賀県", Arrays.asList("84")),
    NS("長崎県", Arrays.asList("85")),
    KU("熊本県", Arrays.asList("86")),
    OI("大分県", Arrays.asList("87")),
    MZ("宮崎県", Arrays.asList("88")),
    KG("鹿児島県", Arrays.asList("89")),
    OK("沖縄県", Arrays.asList("90"));
    
    /** 名前 */
    private String name;
    
    /** 地域番号リスト */
    private List<String> regionNumberList;
    
    /**
     * コンストラクター
     * @param attr
     */
    private LocationCode(String name, List<String> regionNumberList) {
        this.setName(name);
        this.setRegionNumberList(regionNumberList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRegionNumberList() {
        return regionNumberList;
    }

    public void setRegionNumberList(List<String> regionNumberList) {
        this.regionNumberList = regionNumberList;
    }
}
