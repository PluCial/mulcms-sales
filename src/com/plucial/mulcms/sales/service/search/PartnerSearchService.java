package com.plucial.mulcms.sales.service.search;

import org.datanucleus.util.StringUtils;
import org.slim3.util.StringUtil;

import com.google.appengine.api.search.Cursor;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.plucial.mulcms.sales.model.Partner;


public class PartnerSearchService extends SearchService{
    
    private static final String ITEM_DOCUMENT_INDEX = "partner_document_index";
    
    /**
     * 全件検索用のドキュメントを登録 OR 上書き
     * @param company
     */
    public static void put(Partner company) {
        Document document = Document.newBuilder()
                .setId(getDocumentId(company))
                .addField(Field.newBuilder()
                    .setName("keyString")
                    .setAtom(String.valueOf(company.getKey().getName())))
                .addField(Field.newBuilder()
                    .setName("domain")
                    .setAtom(String.valueOf(company.getDomain())))
                .addField(Field.newBuilder()
                    .setName("name")
                    .setAtom(company.getName()))
                .addField(Field.newBuilder()
                    .setName("address")
                    .setAtom(StringUtils.isEmpty(company.getAddress()) ? "" : company.getAddress()))
                .addField(Field.newBuilder()
                    .setName("email")
                    .setAtom(company.getEmail() == null ? "" : company.getEmail().getEmail()))
                .addField(Field.newBuilder()
                    .setName("phoneNumber")
                    .setAtom(company.getPhoneNumber() == null ? "" : company.getPhoneNumber().getNumber()))
                .addField(Field.newBuilder()
                    .setName("responsiblePartyName")
                    .setAtom(StringUtils.isEmpty(company.getResponsiblePartyName()) ? "" : company.getResponsiblePartyName()))
            .build();

            Index index = getDocumentIndex(ITEM_DOCUMENT_INDEX);

            index.put(document);
    }
    
    /**
     * 全件検索検索
     * @param userModel
     * @param content
     */
    protected static Results<ScoredDocument> searchByKeyword(String qstrString) throws Exception {
        
        if(StringUtil.isEmpty(qstrString)) throw new NullPointerException();
        
        // クリエ毎のカーソルを使用
        Cursor cursor = Cursor.newBuilder().setPerResult(false).build();
        
        Index index = getDocumentIndex(ITEM_DOCUMENT_INDEX);
        
        Query query = Query.newBuilder()
                .setOptions(QueryOptions
                    .newBuilder()
                    .setLimit(KEYWORD_SEARCH_ITEM_LIST_LIMIT)
                    .setCursor(cursor)
                    .build())
                    .build(qstrString);
        Results<ScoredDocument> results = index.search(query);
        
        return results;
        
    }
    
    /**
     * キーワード検索
     * @param qstrString
     * @param cursorString
     * @return
     * @throws Exception
     */
    public static Results<ScoredDocument> searchByKeyword(String qstrString, String cursorString) throws Exception {
        if (StringUtil.isEmpty(cursorString)) return searchByKeyword(qstrString);

        Cursor cursor = Cursor.newBuilder().setPerResult(false).build(cursorString);

        Index index = getDocumentIndex(ITEM_DOCUMENT_INDEX);

        Query query = Query.newBuilder()
                .setOptions(QueryOptions
                    .newBuilder()
                    .setLimit(KEYWORD_SEARCH_ITEM_LIST_LIMIT)
                        .setCursor(cursor)
                        .build())
                        .build(qstrString);
        Results<ScoredDocument> results = index.search(query);

        return results;
    }

}
