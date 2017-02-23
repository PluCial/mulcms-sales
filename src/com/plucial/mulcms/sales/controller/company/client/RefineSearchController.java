package com.plucial.mulcms.sales.controller.company.client;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.ClientIndustry;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.service.db.ClientService;

public class RefineSearchController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {

        if(!validate()) {
            List<Company> companyList = new ArrayList<Company>();
            requestScope("companyList", companyList);
            
            return forward("index.jsp");
        }

        ClientIndustry industry = ClientIndustry.valueOf(asString("industry"));
        ContactStatus status = ContactStatus.valueOf(asString("contactStatus"));
        String cursor = asString("cursor");

        S3QueryResultList<Client> companyList = ClientService.getList(industry, status, App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);


        requestScope("companyList", companyList);
        requestScope("nextCursor", companyList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(companyList.hasNext()));

        return forward("index.jsp");
    }
    
    /**
     * バリデーション
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        v.add("industry", v.required());
        v.add("contactStatus", v.required());
        
        return v.validate();
    }
}
