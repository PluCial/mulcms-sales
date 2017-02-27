package com.plucial.mulcms.sales.controller.task;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.PartnerService;
import com.plucial.mulcms.sales.service.mail.InfoMailService;

public class PartnerContactMailTaskController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return null;
        }
        
        // タスクは成功するまで実行されるため、失敗時は例外をキャッチして再実行をさせない
        try {
            Partner company = (Partner)PartnerService.get(asString("company"));
            
            InfoMailService.sendInfoMail(environment, company, true);

        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        v.add("company", v.required());
        
        return v.validate();
    }
    
}
