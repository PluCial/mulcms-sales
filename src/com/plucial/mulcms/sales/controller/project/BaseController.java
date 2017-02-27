package com.plucial.mulcms.sales.controller.project;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.ProjectStatus;
import com.plucial.mulcms.sales.model.Statistics;
import com.plucial.mulcms.sales.service.db.StatisticsService;

public abstract class BaseController extends AppBaseController {

    @Override
    public Navigation execute(Environment environment) throws Exception {
        
        // プロジェクト総数
        Statistics projectTotalNumber = StatisticsService.getNumber(StatisticsService.PROJECT_TOTAL_NUMBER_KEY);
        requestScope("projectTotalNumber", projectTotalNumber);
        
        // ステータス(新規)
        Statistics newProjectStatistics = StatisticsService.getProjectByStatus(ProjectStatus.new_project);
        requestScope("newProjectStatistics", newProjectStatistics);
        
        // ステータス(進行中)
        Statistics inProgressStatistics = StatisticsService.getProjectByStatus(ProjectStatus.in_progress);
        requestScope("inProgressStatistics", inProgressStatistics);

        // ステータス(保留)
        Statistics onHoldStatistics = StatisticsService.getProjectByStatus(ProjectStatus.on_hold);
        requestScope("onHoldStatistics", onHoldStatistics);

        // ステータス(受注)
        Statistics orderedStatistics = StatisticsService.getProjectByStatus(ProjectStatus.ordered);
        requestScope("orderedStatistics", orderedStatistics);

        // ステータス(失注)
        Statistics lossOfOrderStatistics = StatisticsService.getProjectByStatus(ProjectStatus.loss_of_order);
        requestScope("lossOfOrderStatistics", lossOfOrderStatistics);
        
        return exe(environment);
    }
    
    /**
     * リクエスト処理
     * @return
     * @throws Exception
     */
    protected abstract Navigation exe(Environment environment) throws Exception;
}
