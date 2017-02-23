package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.PartnerService;

public class InfoController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        Partner company = (Partner)PartnerService.get(asString("company"));
        requestScope("company", company);
        
        return forward("info.jsp");
    }
}
