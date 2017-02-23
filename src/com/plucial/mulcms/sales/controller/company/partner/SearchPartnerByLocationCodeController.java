package com.plucial.mulcms.sales.controller.company.partner;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.LocationCode;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.service.db.PartnerService;

public class SearchPartnerByLocationCodeController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {

        if(!validate()) {
            List<Company> companyList = new ArrayList<Company>();
            requestScope("companyList", companyList);
            
            return forward("index.jsp");
        }

        LocationCode locationCode = LocationCode.valueOf(asString("locationCode"));
        String cursor = asString("cursor");

        S3QueryResultList<Partner> companyList = PartnerService.searchPartnerByLocationCode(locationCode, App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);


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
        v.add("locationCode", v.required());
        
        return v.validate();
    }
}
