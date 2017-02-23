package com.plucial.mulcms.sales.controller;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;

public class IndexController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        return forward("index.jsp");
    }
}
