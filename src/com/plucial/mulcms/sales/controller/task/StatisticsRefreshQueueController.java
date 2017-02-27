package com.plucial.mulcms.sales.controller.task;

import org.slim3.controller.Navigation;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.StatisticsKey;

public class StatisticsRefreshQueueController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        // キューのタスク追加
        Queue queue = QueueFactory.getDefaultQueue();
        
        for(StatisticsKey key: StatisticsKey.values()) {
            queue.add(TaskOptions.Builder.withUrl("/task/statisticsRefreshTask").param("key", key.toString()));
            
        }
        
        return redirect("/");
    }
    
}
