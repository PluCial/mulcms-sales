package com.plucial.mulcms.sales.controller.project;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.service.db.ClientService;

public class AddController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        Client client = (Client)ClientService.get(asString("client"));
        requestScope("client", client);
        
        requestScope("name", client.getName() + " HP");
        
        return forward("add.jsp");
    }
}
