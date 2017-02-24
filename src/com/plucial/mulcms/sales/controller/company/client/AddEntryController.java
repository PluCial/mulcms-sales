package com.plucial.mulcms.sales.controller.company.client;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.enums.ClientIndustry;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.service.db.ClientService;

public class AddEntryController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/company/client/add");
        }
        
        ClientIndustry clientIndustry = ClientIndustry.valueOf(asString("industry"));
        String name = asString("name");
        String address = asString("address");
        String email = asString("email");
        String homepage = asString("homepage");
        String phoneNumber = asString("phoneNumber");
        String responsiblePartyName = asString("responsiblePartyName");
        
        ClientService.put(clientIndustry, name, email, address, homepage, phoneNumber, responsiblePartyName);
        
        
        return redirect("/company/client/");
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
        
        return v.validate();
    }

    
}
