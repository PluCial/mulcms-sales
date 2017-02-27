package com.plucial.mulcms.sales.controller.task;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.PartnerService;

public class PartnerContactMailQueueController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return null;
        }
        
        /** 配信数 */
        int count = asInteger("count");
        
        // キューのタスク追加
        Queue queue = QueueFactory.getQueue("partner-contact-mail");
        
        List<Partner> companyList = PartnerService.getList(ContactStatus.pending_delivery, count, null);
        
        for(Partner company: companyList) {
            queue.add(TaskOptions.Builder.withUrl("/task/partnerContactMailTask").param("company", company.getKey().getName()));
            
        }
        
        return redirect("/");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        v.add("count", v.required(), v.integerType());
        
        return v.validate();
    }
    
}
