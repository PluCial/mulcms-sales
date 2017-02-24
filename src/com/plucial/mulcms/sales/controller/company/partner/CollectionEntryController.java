package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.service.collect.PartnerCollectService;

public class CollectionEntryController extends BaseController {
    
    @Override
    protected Navigation exe(Environment environment) throws Exception {
     // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/setting/collection");
        }
        
        String url = asString("url");
        PartnerCollectService.Build(url, false);
        
        return redirect("/company/partner/collection");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);

        v.add("url", v.required());
        
        return v.validate();
    }

    
}
