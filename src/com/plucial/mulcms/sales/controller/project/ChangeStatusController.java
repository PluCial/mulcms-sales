package com.plucial.mulcms.sales.controller.project;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.controller.AppBaseController;
import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.enums.ProjectStatus;
import com.plucial.mulcms.sales.model.Project;
import com.plucial.mulcms.sales.service.db.ProjectService;

public class ChangeStatusController extends AppBaseController {

    @Override
    protected Navigation execute(Environment environment) throws Exception {
        Project project = ProjectService.getById(asLong("projectId"));
        String status = asString("status");
        
        ProjectStatus oldStatus = project.getStatus();
        
        ProjectStatus newStatus = ProjectStatus.valueOf(status);
        ProjectService.changeStatus(project, newStatus);
        
        return redirect("/project/?status=" + oldStatus.toString());
    }
}
