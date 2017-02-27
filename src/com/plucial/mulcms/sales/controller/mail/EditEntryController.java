package com.plucial.mulcms.sales.controller.mail;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.MailKey;
import com.plucial.mulcms.sales.model.MailTemplate;
import com.plucial.mulcms.sales.service.db.MailTemplateService;

public class EditEntryController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/mail/edit");
        }
        
        MailKey key = MailKey.valueOf(asString("key"));
        MailTemplate mailTemplate = MailTemplateService.get(key);
        
        MailTemplateService.put(key, asString("fromPersonal"), asString("subject"), asString("content"));
        
        return redirect("/mail/?key=" + mailTemplate.getKey().getName());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        
        v.add("key", v.required());
        v.add("fromPersonal", v.required());
        v.add("subject", v.required());
        v.add("content", v.required());
        
        return v.validate();
    }

    
}
