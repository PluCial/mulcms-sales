package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.PartnerService;

public class EditInfoController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        Partner company = (Partner)PartnerService.get(asString("company"));
        requestScope("company", company);
        
        if(company.getName() != null) {
            requestScope("name", company.getName());
        }
        
        if(company.getHomepage() != null) {
            requestScope("homepage", company.getHomepage());
        }
        
        if(company.getAddress() != null) {
            requestScope("address", company.getAddress());
        }
        
        if(company.getEmail() != null) {
            requestScope("email", company.getEmail().getEmail());
        }
        
        if(company.getPhoneNumber() != null) {
            requestScope("phoneNumber", company.getPhoneNumber().getNumber());
        }
        if(company.getResponsiblePartyName() != null) {
            requestScope("responsiblePartyName", company.getResponsiblePartyName());
        }
        if(company.getDomain() != null) {
            requestScope("domain", company.getDomain());
        }
        
        return forward("edit_info.jsp");
    }
}
