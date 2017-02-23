package com.plucial.mulcms.sales.controller.project;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.ProjectStatus;
import com.plucial.mulcms.sales.model.Project;
import com.plucial.mulcms.sales.service.db.ProjectService;

public class IndexController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        String cursor = asString("cursor");
        
        // ステータス
        String statusString = asString("status");
        
        ProjectStatus status = ProjectStatus.new_project;
        
        if(!StringUtil.isEmpty(statusString)) {
            status = ProjectStatus.valueOf(statusString);
        }
        
        requestScope("status", status);
        
        S3QueryResultList<Project> projectList = ProjectService.getList(status, App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);
        requestScope("projectList", projectList);
        
        requestScope("nextCursor", projectList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(projectList.hasNext()));
        
        return forward("index.jsp");
    }
}
