package com.plucial.mulcms.sales.controller.mail;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.MailKey;
import com.plucial.mulcms.sales.model.MailTemplate;
import com.plucial.mulcms.sales.service.db.MailTemplateService;

public class EditController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        MailKey key = MailKey.valueOf(asString("key"));
        
        MailTemplate mailTemplate = MailTemplateService.get(key);
        requestScope("mailTemplate", mailTemplate);
        
        requestScope("subject", mailTemplate.getSubject());
        requestScope("fromPersonal", mailTemplate.getFromPersonal());
        requestScope("message", mailTemplate.getMessageString());
        
        return forward("edit.jsp");
    }
}
