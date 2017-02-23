package com.plucial.mulcms.sales.controller.company.partner;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.db.StatisticsService;

public abstract class BaseController extends AppBaseController {

    @Override
    public Navigation execute(Environment environment) throws Exception {
        
        // ターゲット数
        Statistics companyTotalNumber = StatisticsService.getNumber(StatisticsService.PARTNER_TOTAL_NUMBER_KEY);
        requestScope("companyTotalNumber", companyTotalNumber);
        
        // パートナー数
        Statistics partnerNumber = StatisticsService.getNumber(StatisticsService.PARTNER_NUMBER_KEY);
        requestScope("partnerNumber", partnerNumber);
        
        // 配信待ち数
        Statistics pendingDeliveryStatistics = StatisticsService.getPartnerStatus(ContactStatus.pending_delivery);
        requestScope("pendingDeliveryStatistics", pendingDeliveryStatistics);
        
        // 配信済数
        Statistics deliveredStatistics = StatisticsService.getPartnerStatus(ContactStatus.delivered);
        requestScope("deliveredStatistics", deliveredStatistics);
        
        // 配信失敗
        Statistics deliveryFailureStatistics = StatisticsService.getPartnerStatus(ContactStatus.delivery_failure);
        requestScope("deliveryFailureStatistics", deliveryFailureStatistics);
        
        return exe(environment);
    }
    
    /**
     * リクエスト処理
     * @return
     * @throws Exception
     */
    protected abstract Navigation exe(Environment environment) throws Exception;
}
