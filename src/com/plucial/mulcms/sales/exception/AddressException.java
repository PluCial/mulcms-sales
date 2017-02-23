package com.plucial.mulcms.sales.exception;

public class AddressException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "権限がありません。";
    
    /**
     * コンストラクタ
     */
    public AddressException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public AddressException(String message) {
        super(message);
    }

}
