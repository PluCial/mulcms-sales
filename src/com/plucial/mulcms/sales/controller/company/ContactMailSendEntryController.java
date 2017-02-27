package com.plucial.mulcms.sales.controller.company;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.MailKey;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.CompanyService;
import com.plucial.mulcms.sales.service.mail.InfoMailService;

public class ContactMailSendEntryController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        Company company = CompanyService.get(asString("company"));
        
        
        
        if(company instanceof Partner) {
            InfoMailService.sendInfoMail(environment, company, MailKey.partner_contact_mail, false);
            
            return redirect("/company/partner/?status=" + ContactStatus.pending_delivery);
            
        }else if(company instanceof Client) {
            InfoMailService.sendInfoMail(environment, company, MailKey.client_contact_mail, false);
            
            return redirect("/company/client/?status=" + ContactStatus.pending_delivery);
            
        }else {
            return redirect("/");
        }

    }
}
