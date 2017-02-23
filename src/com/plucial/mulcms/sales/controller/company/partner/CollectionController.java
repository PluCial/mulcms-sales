package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;

public class CollectionController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        return forward("collection.jsp");
    }
}
