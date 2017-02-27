package com.plucial.mulcms.sales.controller.task;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.StatisticsKey;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.db.ClientService;
import com.plucial.mulcms.sales.service.db.PartnerService;
import com.plucial.mulcms.sales.service.db.StatisticsService;

public class StatisticsRefreshTaskController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return null;
        }
        
        // タスクは成功するまで実行されるため、失敗時は例外をキャッチして再実行をさせない
        try {
            StatisticsKey key = StatisticsKey.valueOf(asString("key"));
            System.out.println(key.toString());
            
            Statistics model = null;
            int count = 0;
            
            if(key == StatisticsKey.client_target_total_number) {
                count = ClientService.countAll();
                
            }else if(key == StatisticsKey.client_contact_pending_delivery) {
                count = ClientService.countStatus(ContactStatus.pending_delivery);
                
            }else if(key == StatisticsKey.client_contact_delivered) {
                count = ClientService.countStatus(ContactStatus.delivered);
                
            }else if(key == StatisticsKey.client_contact_delivery_failure) {
                count = ClientService.countStatus(ContactStatus.delivery_failure);
                
            }else if(key == StatisticsKey.partner_target_total_number) {
                count = PartnerService.countAll();
                
            }else if(key == StatisticsKey.partner_contact_pending_delivery) {
                count = PartnerService.countStatus(ContactStatus.pending_delivery);
                
            }else if(key == StatisticsKey.partner_contact_delivered) {
                count = PartnerService.countStatus(ContactStatus.delivered);
                
            }else if(key == StatisticsKey.partner_contact_delivery_failure) {
                count = PartnerService.countStatus(ContactStatus.delivery_failure);
                
            }
            
            model = StatisticsService.getNumber(key.toString());
            model.setStatistic(count);
            StatisticsService.put(model);

        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        v.add("key", v.required());
        
        return v.validate();
    }
    
}
