package com.plucial.mulcms.sales.service.db;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.sales.dao.ClientDao;
import com.plucial.mulcms.sales.enums.ClientIndustry;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.exception.ObjectNotExistException;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.search.ClientSearchService;


public class ClientService extends CompanyService {
    
    /** DAO */
    private static final ClientDao dao = new ClientDao();
    
    /**
     * PUT
     * @param clientIndustry
     * @param name
     * @param email
     * @param address
     * @param homepage
     * @param phoneNumber
     * @param responsiblePartyName
     * @return
     */
    public static Client put(ClientIndustry clientIndustry, String name, String email, String address, String homepage, String phoneNumber, String responsiblePartyName) {
        Client model = null;
        
        String companyDomain = getCompanyDomainByEmail(email);
        
        try {
            model = (Client) get(companyDomain);
            return model;

        } catch (ObjectNotExistException e) {

            // 新規作成
            Transaction tx = Datastore.beginTransaction();
            try {
                model = new Client();


                model.setKey(createKey());
                model.setDomain(StringUtil.isEmpty(companyDomain) ? null : companyDomain);
                model.setIndustry(clientIndustry);
                model.setName(StringUtil.isEmpty(name) ? null : name.trim());
                model.setHomepage(StringUtil.isEmpty(homepage) ? null : homepage.trim());
                model.setAddress(StringUtil.isEmpty(address) ? null : address.trim());
                
                model.setEmail(StringUtil.isEmpty(email) ? null : new Email(email.trim()));
                model.setPhoneNumber(StringUtil.isEmpty(phoneNumber) ? null : new PhoneNumber(phoneNumber.trim()));
                model.setResponsiblePartyName(StringUtil.isEmpty(responsiblePartyName) ? null : responsiblePartyName.trim());

                Datastore.put(tx, model);

                // 会社総数の加算
                Statistics statistics = StatisticsService.getNumber(StatisticsService.CLIENT_TOTAL_NUMBER_KEY);
                statistics.setStatistic(statistics.getStatistic() + 1);
                StatisticsService.put(tx, statistics);
                
                // 未配信数の加算
                Statistics pendingDeliveryStatistics = StatisticsService.getClientByStatus(ContactStatus.pending_delivery);
                pendingDeliveryStatistics.setStatistic(pendingDeliveryStatistics.getStatistic() + 1);
                StatisticsService.put(tx, pendingDeliveryStatistics);
                
                
                // 検索対象にする
                ClientSearchService.put(model);

                tx.commit();

            }finally {
                if(tx.isActive()) {
                    tx.rollback();
                }
            }
        }
        
        return model;
    }
    
    /**
     * PUT
     * @param clientIndustry
     * @param name
     * @param email
     * @param address
     * @return
     */
    public static Client put(ClientIndustry clientIndustry, String name, String email, String address) {
        return put(clientIndustry, name, email, address, null, null, null);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void put(Client model) {
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, model);
            
            // 検索対象を更新
            ClientSearchService.put(model);
            
            tx.commit();
            
        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * リストの取得
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Client> getList(int limit, String cursor) {
        return dao.getList(limit, cursor);
    }
    
    /**
     * リストの取得
     * @param status
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Client> getList(ContactStatus status, int limit, String cursor) {
        return dao.getList(status, limit, cursor);
    }
    
    /**
     * リストの取得
     * @param clientIndustry
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Client> getList(ClientIndustry clientIndustry, int limit, String cursor) {
        return dao.getList(clientIndustry, limit, cursor);
    }
    
    /**
     * リストの取得
     * @param clientIndustry
     * @param status
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Client> getList(ClientIndustry clientIndustry, ContactStatus status, int limit, String cursor) {
        return dao.getList(clientIndustry, status, limit, cursor);
    }
    
    /**
     * カウントの取得
     * @return
     */
    public static int countAll() {
        return dao.countAll();
    }
    
    /**
     * ステータス毎のカウント
     * @param status
     * @return
     */
    public static int countStatus(ContactStatus status) {
        return dao.countStatus(status);
    }

}
