package com.plucial.mulcms.sales.controller.company.client;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;

public class AddController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        return forward("add.jsp");
    }
}
