package com.plucial.mulcms.sales.controller.project;

import org.slim3.controller.Navigation;

import com.plucial.mulcms.sales.enums.Environment;
import com.plucial.mulcms.sales.model.Partner;
import com.plucial.mulcms.sales.model.Project;
import com.plucial.mulcms.sales.service.db.PartnerService;
import com.plucial.mulcms.sales.service.db.ProjectService;

public class AddPartnerEntryController extends BaseController {

    @Override
    protected Navigation exe(Environment environment) throws Exception {
        
        Project project = ProjectService.getById(asLong("projectId"));
        Partner partner = (Partner)PartnerService.get(asString("partner"));
        
        ProjectService.addPartner(project, partner);
        
        return redirect("/project/");
    }
    
}
