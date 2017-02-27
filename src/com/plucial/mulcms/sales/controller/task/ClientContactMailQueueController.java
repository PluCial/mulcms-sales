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
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.service.db.ClientService;

public class ClientContactMailQueueController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return null;
        }
        
        /** 配信数 */
        int count = asInteger("count");
        
        // キューのタスク追加
        Queue queue = QueueFactory.getQueue("client-contact-mail");
        
        List<Client> companyList = ClientService.getList(ContactStatus.pending_delivery, count, null);
        
        for(Client company: companyList) {
            queue.add(TaskOptions.Builder.withUrl("/task/clientContactMailTask").param("company", company.getKey().getName()));
            
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
