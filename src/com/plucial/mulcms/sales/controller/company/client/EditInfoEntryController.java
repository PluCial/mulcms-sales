package com.plucial.mulcms.sales.controller.company.client;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;
import com.plucial.mulcms.sales.enums.ClientIndustry;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.service.db.ClientService;
import com.plucial.mulcms.sales.service.db.CompanyService;

public class EditInfoEntryController extends BaseController {

    @Override
    public Navigation exe(Environment environment) throws Exception {
        
        Client company = (Client)CompanyService.get(asString("company"));
        requestScope("company", company);
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("edit_info.jsp");
        }
        
        ClientIndustry clientIndustry = ClientIndustry.valueOf(asString("industry"));
        company.setIndustry(clientIndustry);
        company.setName(StringUtil.isEmpty(asString("name")) ? null : asString("name").trim());
        company.setHomepage(StringUtil.isEmpty(asString("homepage")) ? null : asString("homepage").trim());
        company.setAddress(StringUtil.isEmpty(asString("address")) ? null : asString("address").trim());
        company.setDomain(StringUtil.isEmpty(asString("domain")) ? null : asString("domain").trim());
        company.setEmail(StringUtil.isEmpty(asString("domain")) ? null : new Email(asString("email").trim()));
        company.setPhoneNumber(StringUtil.isEmpty(asString("domain")) ? null : new PhoneNumber(asString("phoneNumber").trim()));
        company.setResponsiblePartyName(StringUtil.isEmpty(asString("responsiblePartyName")) ? null : asString("responsiblePartyName").trim());
        
        ClientService.put(company);
        
        return redirect("/company/client/info?company=" + company.getKey().getName());
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        v.add("industry", v.required());
        v.add("name", v.required());
        
        // メール
        v.add("email", 
            v.required("メールアドレスを入力してください。"),
            v.maxlength(256, "メールアドレスが長すぎます。"), 
            v.minlength(6, "メールアドレスが短すぎます。"),
            v.regexp("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*([,;]\\s*\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*", "メールアドレスが正しくありません。"));
        
        v.add("address", v.required());
        v.add("domain", v.required());
        
        return v.validate();
    }
}
