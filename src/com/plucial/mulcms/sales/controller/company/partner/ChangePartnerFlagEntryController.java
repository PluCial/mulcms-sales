package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.PartnerService;
import com.plucial.mulcms.sales.validator.NGValidator;

public class ChangePartnerFlagEntryController extends BaseController {
    
    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        Partner model = (Partner)PartnerService.get(asString("company"));

        try {
            PartnerService.changePartnerFlag(model);
            
        } catch (Exception e) {
            Validators v = new Validators(request);
            
            v.add("company",
                new NGValidator("住所が正しくないか、もしくは完全な住所ではありません。正しい住所に変更してから再度実行してください。"));
            
            v.validate();
            
            requestScope("company", model);
            
            if(model.getName() != null) {
                requestScope("name", model.getName());
            }
            
            if(model.getHomepage() != null) {
                requestScope("homepage", model.getHomepage());
            }
            
            if(model.getAddress() != null) {
                requestScope("address", model.getAddress());
            }
            
            if(model.getEmail() != null) {
                requestScope("email", model.getEmail().getEmail());
            }
            
            if(model.getPhoneNumber() != null) {
                requestScope("phoneNumber", model.getPhoneNumber().getNumber());
            }
            if(model.getResponsiblePartyName() != null) {
                requestScope("responsiblePartyName", model.getResponsiblePartyName());
            }
            if(model.getDomain() != null) {
                requestScope("domain", model.getDomain());
            }
            
            return forward("edit_info.jsp");
        }
        
        
        return redirect("/company/partner/");
    } 
}
