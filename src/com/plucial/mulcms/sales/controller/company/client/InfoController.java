package com.plucial.mulcms.sales.controller.company.client;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Project;
import com.plucial.mulcms.sales.service.db.ClientService;
import com.plucial.mulcms.sales.service.db.ProjectService;

public class InfoController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        String cursor = asString("cursor");
        
        Client company = (Client)ClientService.get(asString("company"));
        requestScope("company", company);
        
        S3QueryResultList<Project> projectList = ProjectService.getList(company, App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);
        requestScope("projectList", projectList);
        
        return forward("info.jsp");
    }
}
