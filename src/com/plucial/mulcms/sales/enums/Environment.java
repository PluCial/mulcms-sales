package com.plucial.mulcms.sales.enums;


/**
 * 環境
 * @author takahara
 */
public enum Environment {
    /**
     * 本番環境
     */
    Local("MulCMS-Sales", "AIzaSyBjd4YrXnr7DyOCHN7WL0VIQ5uHo2MStg8"),
    
    /**
     * 本番環境(プロダクション環境)
     */
    production("MulCMS-Sales", "AIzaSyBjd4YrXnr7DyOCHN7WL0VIQ5uHo2MStg8");
    
    /** アプリケーション名(Google API 用) */
    private String googleApiApplicationName;
    
    /** 翻訳APIの公開キー */
    private String googleApiPublicServerKey;
    
    /**
     * コンストラクター
     * @param googleApiApplicationName
     * @param googleApiPublicServerKey
     * @param googleProjectClientId
     * @param googleProjectClientSecret
     * @param projectAdminAccountEmail
     * @param gcsBucketName
     * @param paypalExpressCheckoutUser
     * @param paypalExpressCheckoutPwd
     * @param paypalExpressCheckoutSignature
     */
    private Environment(
            String googleApiApplicationName, 
            String googleApiPublicServerKey) {
        
        this.setGoogleApiApplicationName(googleApiApplicationName);
        this.setGoogleApiPublicServerKey(googleApiPublicServerKey);
    }

    public String getGoogleApiApplicationName() {
        return googleApiApplicationName;
    }

    public void setGoogleApiApplicationName(String googleApiApplicationName) {
        this.googleApiApplicationName = googleApiApplicationName;
    }

    public String getGoogleApiPublicServerKey() {
        return googleApiPublicServerKey;
    }

    public void setGoogleApiPublicServerKey(String googleApiPublicServerKey) {
        this.googleApiPublicServerKey = googleApiPublicServerKey;
    }
}
