package com.plucial.mulcms.sales.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModificationDate;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PhoneNumber;
import com.plucial.mulcms.sales.enums.ContactStatus;
import com.plucial.mulcms.sales.enums.LocationCode;
import com.plucial.mulcms.sales.service.geo.GeoModel;

@Model(schemaVersion = 1)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ドメインをキーに設定
     */
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** ステータス */
    private ContactStatus contactStatus = ContactStatus.pending_delivery;
    
    /**
     * ドメイン
     */
    private String domain;
    
    /**
     * 都道府県コード
     */
    private LocationCode locationCode;
    
    /**
     * 位置情報
     */
    @Attribute(lob = true)
    private GeoModel geoModel;
    
    /**
     * 名前
     */
    @Attribute(unindexed = true)
    private String name;
    
    /**
     * HP
     */
    @Attribute(unindexed = true)
    private String homepage;
    
    /**
     * 住所
     */
    @Attribute(unindexed = true)
    private String address;
    
    /**
     * Email
     */
    @Attribute(unindexed = true)
    private Email email;
    
    /**
     * Email を持っている
     */
//    private boolean hasEmail = false;
//    
//    /** info メールではない */
//    private boolean notInfoMail = false;
    
    /**
     * 電話番号
     */
    @Attribute(unindexed = true)
    private PhoneNumber phoneNumber;
    
    /**
     * 責任者名
     */
    private String responsiblePartyName;
    
    /**
     * 作成日時
     */
    @Attribute(listener = CreationDate.class)
    private Date createDate;
    
    /**
     * 更新日時
     */
    @Attribute(listener = ModificationDate.class)
    private Date updateDate;

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Company other = (Company) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResponsiblePartyName() {
        return responsiblePartyName;
    }

    public void setResponsiblePartyName(String responsiblePartyName) {
        this.responsiblePartyName = responsiblePartyName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public LocationCode getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(LocationCode locationCode) {
        this.locationCode = locationCode;
    }

    public GeoModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(GeoModel geoModel) {
        this.geoModel = geoModel;
    }

    public ContactStatus getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(ContactStatus contactStatus) {
        this.contactStatus = contactStatus;
    }
}
