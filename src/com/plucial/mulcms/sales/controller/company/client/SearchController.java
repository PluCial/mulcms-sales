package com.plucial.mulcms.sales.controller.company.client;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.service.search.ClientSearchService;
import com.plucial.mulcms.sales.service.search.SearchService;

public class SearchController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        // リストを取得
        String keyword = asString("keyword");
        List<Company> companyList = null;
        
        if(StringUtil.isEmpty(keyword)) {
            companyList = new ArrayList<Company>();

        }else {
            Results<ScoredDocument> results = ClientSearchService.searchByKeyword(keyword, null);
            companyList = SearchService.getListByResults(results);
        }
        requestScope("companyList", companyList);
        
        return forward("index.jsp");
    }
}
