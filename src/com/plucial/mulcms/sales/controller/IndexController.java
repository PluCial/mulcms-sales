package com.plucial.mulcms.sales.controller;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.db.StatisticsService;

public class IndexController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        
        Statistics clientTotalNumber = StatisticsService.getNumber(StatisticsService.CLIENT_TOTAL_NUMBER_KEY);
        requestScope("clientTotalNumber", clientTotalNumber);
        
        Statistics partnerTotalNumber = StatisticsService.getNumber(StatisticsService.PARTNER_TOTAL_NUMBER_KEY);
        requestScope("partnerTotalNumber", partnerTotalNumber);
        
        Statistics projectTotalNumber = StatisticsService.getNumber(StatisticsService.PROJECT_TOTAL_NUMBER_KEY);
        requestScope("projectTotalNumber", projectTotalNumber);
        
        return forward("index.jsp");
    }
}
