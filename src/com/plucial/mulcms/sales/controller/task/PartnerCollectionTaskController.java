package com.plucial.mulcms.sales.controller.task;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.service.collect.PartnerCollectService;

public class PartnerCollectionTaskController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        String url = asString("url");
        
        // タスクは成功するまで実行されるため、失敗時は例外をキャッチして再実行をさせない
        try {
            PartnerCollectService.Build(url, true);

        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
