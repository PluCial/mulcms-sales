package com.plucial.mulcms.sales.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ServletContextLocator;

import com.plucial.mulcms.sales.enums.Environment;

public abstract class AppBaseController extends Controller {
    
    /**
     * 開発環境かどうか
     * @return
     */
    public boolean isLocal(){
        return ServletContextLocator.get().getServerInfo().indexOf("Development") >= 0;
    }
    
    /**
     * 実行環境の取得
     * @return
     */
    protected Environment getEnvironment() {
        if(isLocal()) return Environment.Local;
        
        return Environment.production;
    }

    @Override
    public Navigation run() throws Exception {
        
        return execute(getEnvironment());
    }
    
    /**
     * リクエスト処理
     * @return
     * @throws Exception
     */
    protected abstract Navigation execute(Environment environment) throws Exception;
}
