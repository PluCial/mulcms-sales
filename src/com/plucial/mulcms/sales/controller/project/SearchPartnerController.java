package com.plucial.mulcms.sales.controller.project;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.plucial.mulcms.sales.App;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Project;
import com.plucial.mulcms.sales.service.db.PartnerService;
import com.plucial.mulcms.sales.service.db.ProjectService;
import com.plucial.mulcms.sales.service.search.PartnerSearchService;
import com.plucial.mulcms.sales.service.search.SearchService;

public class SearchPartnerController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        Project project = ProjectService.getById(asLong("projectId"));
        requestScope("project", project);
        
        Client client = project.getClientRef().getModel();
        requestScope("client", client);

        String keyword = asString("keyword");
        
        if(!StringUtil.isEmpty(keyword)) {
            // キーワード検索
            Results<ScoredDocument> results = PartnerSearchService.searchByKeyword(keyword, null);
            List<Company> companyList = SearchService.getListByResults(results);
            
            requestScope("companyList", companyList);
            
        }else {
            // 都道府県検索
            String cursor = asString("cursor");
            
            S3QueryResultList<Partner> companyList = PartnerService.searchPartnerByLocationCode(client.getLocationCode(), App.LIST_NUMBER_OF_ITEMS, StringUtil.isEmpty(cursor) ? null : cursor);
            requestScope("companyList", companyList);
            requestScope("nextCursor", companyList.getEncodedCursor());
            requestScope("hasNext", String.valueOf(companyList.hasNext()));
        }
        
        return forward("search_partner.jsp");
    }
}
