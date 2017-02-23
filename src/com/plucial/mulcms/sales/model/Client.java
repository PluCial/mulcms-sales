package com.plucial.mulcms.sales.model;

import java.io.Serializable;

import org.slim3.datastore.Model;

import com.plucial.mulcms.sales.enums.ClientIndustry;

@Model(schemaVersion = 1)
public class Client extends Company implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 業種
     */
    private ClientIndustry industry;
    

    public ClientIndustry getIndustry() {
        return industry;
    }

    public void setIndustry(ClientIndustry industry) {
        this.industry = industry;
    }

}
