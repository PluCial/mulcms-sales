package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.CompanyService;
import com.plucial.mulcms.sales.service.db.PartnerService;

public class EditInfoEntryController extends BaseController {

    @Override
    public Navigation exe(Environment environment) throws Exception {
        
        Partner company = (Partner)CompanyService.get(asString("company"));
        requestScope("company", company);
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("edit_info.jsp");
        }
        
        company.setName(StringUtil.isEmpty(asString("name")) ? null : asString("name").trim());
        company.setHomepage(StringUtil.isEmpty(asString("homepage")) ? null : asString("homepage").trim());
        company.setAddress(StringUtil.isEmpty(asString("address")) ? null : asString("address").trim());
        company.setDomain(StringUtil.isEmpty(asString("domain")) ? null : asString("domain").trim());
        company.setEmail(StringUtil.isEmpty(asString("domain")) ? null : new Email(asString("email").trim()));
        company.setPhoneNumber(StringUtil.isEmpty(asString("domain")) ? null : new PhoneNumber(asString("phoneNumber").trim()));
        company.setResponsiblePartyName(StringUtil.isEmpty(asString("responsiblePartyName")) ? null : asString("responsiblePartyName").trim());
        
        PartnerService.put(company);
        
        return redirect("/company/partner/info?company=" + company.getKey().getName());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        v.add("name", v.required());
        v.add("homepage", v.required());
        v.add("address", v.required());
        v.add("domain", v.required());
        
        return v.validate();
    }
}
