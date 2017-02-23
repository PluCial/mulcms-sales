package com.plucial.mulcms.sales.controller.company;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.CompanyService;

public class ChangeStatusController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        Company company = CompanyService.get(asString("company"));
        String status = asString("status");
        
        ContactStatus oldStatus = company.getContactStatus();
        
        ContactStatus newStatus = ContactStatus.valueOf(status);
        CompanyService.changeStatus(company, newStatus);
        
        if(company instanceof Partner) {
            return redirect("/company/partner/?status=" + oldStatus.toString());
            
        }else if(company instanceof Client) {
            return redirect("/company/client/?status=" + oldStatus.toString());
            
        }else {
            return redirect("/");
        }
    }
}
