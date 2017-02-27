package com.plucial.mulcms.sales.controller.task;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.Task;

public class IndexController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        if(StringUtil.isEmpty(asString("type"))) {
            return redirect("/task/?type=" + Task.partner_collection.toString());
        }
        
        Task task = Task.valueOf(asString("type"));
        requestScope("task", task);
        
        return forward(task.toString() + ".jsp");
    }
}
