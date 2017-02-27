package com.plucial.mulcms.sales.controller.mail;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.MailKey;
import com.plucial.mulcms.sales.model.MailTemplate;
import com.plucial.mulcms.sales.service.db.MailTemplateService;

public class IndexController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        if(StringUtil.isEmpty(asString("key"))) {
            return redirect("/mail/?key=" + MailKey.partner_contact_mail.toString());
        }
        
        MailKey key = MailKey.valueOf(asString("key"));
        
        MailTemplate mailTemplate = MailTemplateService.get(key);
        requestScope("mailTemplate", mailTemplate);
        
        return forward("index.jsp");
    }
}
