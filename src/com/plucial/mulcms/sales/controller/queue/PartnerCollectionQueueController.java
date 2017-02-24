package com.plucial.mulcms.sales.controller.queue;

import org.slim3.controller.Navigation;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;

public class PartnerCollectionQueueController extends AppBaseController {
    
    private static final int maxPage = 699;

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        // キューのタスク追加
        Queue queue = QueueFactory.getQueue("partner-collection");
        
        for(int i=0; i < (environment == Environment.Local ? 2 : maxPage); i++) {
            String urlStr = "https://imitsu.jp/list/hp-design/?pn=";
            int page = i + 1;
            System.out.println("Queue: " + urlStr + page);
            queue.add(TaskOptions.Builder.withUrl("/queue/partnerCollectionTask").param("url", urlStr + page));
            
        }
        
        return redirect("/");
    }
    
}
