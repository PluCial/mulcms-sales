package com.plucial.mulcms.sales.controller.company;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.CompanyService;
import com.plucial.mulcms.sales.service.mail.InfoMailService;

public class ContactMailSendEntryController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        Company company = CompanyService.get(asString("company"));
        
        InfoMailService.sendInfoMail(environment, company, false);
        
        if(company instanceof Partner) {
            return redirect("/company/partner/?status=" + ContactStatus.pending_delivery);
            
        }else if(company instanceof Client) {
            return redirect("/company/client/?status=" + ContactStatus.pending_delivery);
            
        }else {
            return redirect("/");
        }

    }
}
