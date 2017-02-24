package com.plucial.mulcms.sales.controller.company.client;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.service.db.ClientService;

public class IndexController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        String cursor = asString("cursor");
        
        // ステータス
        String statusString = asString("status");
        ContactStatus status = ContactStatus.pending_delivery;
        
        if(!StringUtil.isEmpty(statusString)) {
            status = ContactStatus.valueOf(statusString);       
        }
        
        requestScope("status", status);
        S3QueryResultList<? extends Company> companyList = ClientService.getList(status, App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);
        
        requestScope("companyList", companyList);
        requestScope("nextCursor", companyList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(companyList.hasNext()));
        
        return forward("index.jsp");
    }
}
