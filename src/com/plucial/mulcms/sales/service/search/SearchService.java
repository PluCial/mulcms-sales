package com.plucial.mulcms.sales.service.search;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.StringUtil;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.plucial.mulcms.sales.exception.ObjectNotExistException;
import com.plucial.mulcms.sales.model.Company;
import com.plucial.mulcms.sales.service.db.CompanyService;


public class SearchService {
    
    protected static final int KEYWORD_SEARCH_ITEM_LIST_LIMIT = 100;

    /**
     * インデックスの取得
     * @param userModel
     * @return
     */
    protected static Index getDocumentIndex(String indexKey) {
        return SearchServiceFactory.getSearchService()
                .getIndex(IndexSpec.newBuilder()
                    .setName(indexKey));
    }
    
    /**
     * ドキュメントのキーを取得
     * @param item
     * @param lang
     * @return
     */
    protected static String getDocumentId(Company company) {
        return company.getKey().getName();
    }
    
    /**
     * documentId からドキュメントを取得
     * @param item
     * @param lang
     * @return
     */
    public static Document getDocumentById(String indexKey, Company company) {
        String documentId = getDocumentId(company);
        
        Index index = getDocumentIndex(indexKey);
        
        return index.get(documentId);
    }
    
    /**
     * 検索結果から会社リストを生成
     * @param results
     * @return
     */
    public static List<Company> getListByResults(Results<ScoredDocument> results) {
        // 対象のアイテムリストを取得
        List<Company> list = new ArrayList<Company>();
        for (ScoredDocument document : results) {
            String companyKey = document.getOnlyField("keyString").getAtom();
            
            if(!StringUtil.isEmpty(companyKey)) {
                Company company = null;
                try {
                    company = CompanyService.get(companyKey);
                    list.add(company);
                    
                } catch (ObjectNotExistException e) {
                }
            }
        }
        
        return list;
    }

}
