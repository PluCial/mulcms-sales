package com.plucial.mulcms.sales.model;

import java.io.Serializable;

import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Partner extends Company implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * パートナーフラグ
     */
    private boolean partner;
    
    /**
     * 案件数
     */
    private int numberOfProjects = 0;

    public boolean isPartner() {
        return partner;
    }

    public void setPartner(boolean partner) {
        this.partner = partner;
    }

    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(int numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

}
