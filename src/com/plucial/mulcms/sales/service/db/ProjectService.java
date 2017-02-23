package com.plucial.mulcms.sales.service.db;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;

import com.google.appengine.api.datastore.Transaction;
import com.plucial.mulcms.sales.dao.ProjectDao;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.ProjectStatus;
import com.plucial.mulcms.sales.exception.AddressException;
import com.plucial.mulcms.sales.exception.GeocodeStatusException;
import com.plucial.mulcms.sales.exception.GeocoderLocationTypeException;
import com.plucial.mulcms.sales.model.Client;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Project;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.geo.GeoModel;
import com.plucial.mulcms.sales.service.geo.GeoService;

public class ProjectService {
    
    /** DAO */
    private static final ProjectDao dao = new ProjectDao();
    
    /**
     * 取得
     * @param id
     * @return
     */
    public static Project getById(long id) {
        return dao.get(Datastore.createKey(Project.class, id));
    }
    
    /**
     * プロジェクトの登録
     * @param client
     * @param name
     * @return
     * @throws AddressException 
     * @throws GeocoderLocationTypeException 
     * @throws IOException 
     * @throws GeocodeStatusException 
     * @throws InvalidKeyException 
     */
    public static Project put(Client client, String name) throws InvalidKeyException, GeocodeStatusException, IOException, GeocoderLocationTypeException, AddressException {
        
        Project model = null;
        // 新規作成
        Transaction tx = Datastore.beginTransaction();
        try {
            model = new Project();
            model.setName(name);
            model.getClientRef().setModel(client);
            Datastore.put(tx, model);
            
            // 住所情報の取得
            if((client.getLocationCode() == null || client.getGeoModel() == null)) {
                GeoModel geoModel = GeoService.getGeoModel(Environment.production, client.getAddress());
                client.setGeoModel(geoModel);
                client.setLocationCode(CompanyService.getLocationCode(geoModel));
                Datastore.put(tx, client);
            }
            
            // プロジェクト総数を加算
            Statistics projectTotalStatistics = StatisticsService.getNumber(StatisticsService.PROJECT_TOTAL_NUMBER_KEY);
            projectTotalStatistics.setStatistic(projectTotalStatistics.getStatistic() + 1);
            Datastore.put(tx, projectTotalStatistics);
            
            // ステータス(新規)のプロジェクト総数を加算
            Statistics newProjectStatistics = StatisticsService.getProjectStatus(ProjectStatus.new_project);
            newProjectStatistics.setStatistic(newProjectStatistics.getStatistic() + 1);
            Datastore.put(tx, newProjectStatistics);
            
            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }

        return model;
    }
    
    /**
     * パートナーの追加
     * @param project
     * @param partner
     * @return
     */
    public static Project addPartner(Project model, Partner partner) {
        
        if(!partner.isPartner()) return null;

        Transaction tx = Datastore.beginTransaction();
        try {
            
            // パートナーのプロジェクト数の加算
            partner.setNumberOfProjects(partner.getNumberOfProjects() + 1);
            Datastore.put(tx, partner);
            
            // 進行中プロジェクト数の加算
            Statistics projectInProgressStatistics = StatisticsService.getProjectStatus(ProjectStatus.in_progress);
            projectInProgressStatistics.setStatistic(projectInProgressStatistics.getStatistic() + 1);
            Datastore.put(tx, projectInProgressStatistics);
            
            // 元のスタータスの数を減算
            Statistics projectOldStatusStatistics = StatisticsService.getProjectStatus(model.getStatus());
            projectOldStatusStatistics.setStatistic(projectOldStatusStatistics.getStatistic() - 1);
            Datastore.put(tx, projectOldStatusStatistics);
            
            // モデル
            model.getPartnerRef().setModel(partner);
            model.setStatus(ProjectStatus.in_progress);
            Datastore.put(tx, model);

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }

        return model;
    }
    
    /**
     * プロジェクトの削除
     * @param project
     */
    public static void delete(Project model) {
        
        Transaction tx = Datastore.beginTransaction();
        try {
            
            if(model.getPartnerRef() != null) {
                // パートナーの取得
                Partner partner = model.getPartnerRef().getModel();
                // パートナーのプロジェクト数の減算
                partner.setNumberOfProjects(partner.getNumberOfProjects() - 1);
                Datastore.put(tx, partner);
            }
            
            // 現在のステータースの総数の減算
            Statistics projectOldStatusStatistics = StatisticsService.getProjectStatus(model.getStatus());
            projectOldStatusStatistics.setStatistic(projectOldStatusStatistics.getStatistic() - 1);
            Datastore.put(tx, projectOldStatusStatistics);
            
            // プロジェクト総数の減算
            Statistics projectTotalStatistics = StatisticsService.getNumber(StatisticsService.PROJECT_TOTAL_NUMBER_KEY);
            projectTotalStatistics.setStatistic(projectTotalStatistics.getStatistic() - 1);
            Datastore.put(tx, projectTotalStatistics);
            
            // プロジェクトの削除
            Datastore.delete(tx, model.getKey());

            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
        
    }
    
    /**
     * ステータスの更新
     * @param model
     * @param newStatus
     */
    public static void changeStatus(Project model, ProjectStatus newStatus) {
        
        // ステータスチェック
        if(newStatus == ProjectStatus.new_project ||  model.getStatus() == newStatus) return;
        
        // 元の統計Model
        Statistics oldStatistics = StatisticsService.getProjectStatus(model.getStatus());
        // 新しい統計Model
        Statistics newStatistics = StatisticsService.getProjectStatus(newStatus);

        
        Transaction tx = Datastore.beginTransaction();
        try {
            // 減算
            oldStatistics.setStatistic(oldStatistics.getStatistic() - 1);
            StatisticsService.put(tx, oldStatistics);
            // 加算
            newStatistics.setStatistic(newStatistics.getStatistic() + 1);
            StatisticsService.put(tx, newStatistics);
            
            // ステータス変更
            model.setStatus(newStatus);
            Datastore.put(tx, model);
            
            tx.commit();

        }finally {
            if(tx.isActive()) {
                tx.rollback();
            }
        }
    }
    
    /**
     * リストの取得
     * @param status
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Project> getList(ProjectStatus status, int limit, String cursor) {
        return dao.getList(status, limit, cursor);
    }
    
    /**
     * リストの取得
     * @param partner
     * @param limit
     * @param cursor
     * @return
     */
    public static S3QueryResultList<Project> getList(Partner partner, int limit, String cursor) {
        return dao.getList(partner, limit, cursor);
    }
    
    /**
     * リストの取得
     * @param client
     * @param limit
     * @param cursor
     * @return
     */
    public S3QueryResultList<Project> getList(Client client, int limit, String cursor) {
        return dao.getList(client, limit, cursor);
    }

}
