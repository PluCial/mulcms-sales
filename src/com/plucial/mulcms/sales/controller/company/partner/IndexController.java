package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.service.db.PartnerService;

public class IndexController extends BaseController {


    @Override
    protected Navigation exe(Environment environment) throws Exception {
        String cursor = asString("cursor");
        
        // ステータス
        String statusString = asString("status");
        S3QueryResultList<? extends Company> companyList = null;
        
        if(StringUtil.isEmpty(statusString)) {
            companyList = PartnerService.getPartnerList(App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);
                
        }else {
            ContactStatus status = ContactStatus.valueOf(statusString);
            requestScope("status", status);
            
            companyList = PartnerService.getList(status, App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);
        }
        
        requestScope("companyList", companyList);
        requestScope("nextCursor", companyList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(companyList.hasNext()));
        
        return forward("index.jsp");
    }
}
