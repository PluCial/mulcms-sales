package com.plucial.mulcms.sales.controller.project;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.service.db.ClientService;
import com.plucial.mulcms.sales.service.db.ProjectService;
import com.plucial.mulcms.sales.validator.NGValidator;

public class AddEntryController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/project/add");
        }
        String name = asString("name");
        Client client = (Client)ClientService.get(asString("client"));
        
        try {
        ProjectService.put(client, name);
        } catch (Exception e) {
            Validators v = new Validators(request);
            
            v.add("client",
                new NGValidator("クライアントの住所が正しくないか、もしくは完全な住所ではありません。正しい住所に変更してから再度実行してください。"));
            
            v.validate();
        }
        
        return redirect("/project/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        
        v.add("client", v.required());
        v.add("name", v.required());
        
        return v.validate();
    }

    
}
