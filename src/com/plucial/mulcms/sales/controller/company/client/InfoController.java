package com.plucial.mulcms.sales.controller.company.client;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.service.db.ClientService;

public class InfoController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        Client company = (Client)ClientService.get(asString("company"));
        requestScope("company", company);
        
        return forward("info.jsp");
    }
}
