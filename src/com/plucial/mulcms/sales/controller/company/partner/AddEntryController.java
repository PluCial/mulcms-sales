package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.service.db.PartnerService;

public class AddEntryController extends BaseController {
    
    @Override
    protected Navigation exe(Environment environment) throws Exception {
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/company/partner/add");
        }

        String name = asString("name");
        String homepage = asString("homepage");
        String address = asString("address");
        String email = asString("email");
        String phoneNumber = asString("phoneNumber");
        String responsiblePartyName = asString("responsiblePartyName");
        
        PartnerService.put(name, homepage, address, email, phoneNumber, responsiblePartyName, false);
        
        
        return redirect("/company/partner/");
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
        
        return v.validate();
    }

    
}
