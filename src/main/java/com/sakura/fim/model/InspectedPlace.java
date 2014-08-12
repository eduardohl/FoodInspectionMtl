package com.sakura.fim.model;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model used to manipulate inspection 
 * eduardolomonaco
 */
public class InspectedPlace {

    private Integer amount;
    private String owner;
    private String category;
    private String establishment;
    private String address;
    private String city;
    private String description;
    // TODO convert to JODA, issue: Mongo doesn't have the mapping converters,
    // have to create
    private Date infractionDate;
    private Date judgementDate;
    private Date importedDate;

    private String latitude;
    private String longitude;
    private Integer version;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInfractionDate() {
        return infractionDate;
    }

    public void setInfractionDate(Date infractionDate) {
        this.infractionDate = infractionDate;
    }

    public Date getJudgmentDate() {
        return judgementDate;
    }

    public void setJudgementDate(Date judgementDate) {
        this.judgementDate = judgementDate;
    }

    public Date getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(Date importedDate) {
        this.importedDate = importedDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31)
            .append(amount)
            .append(owner)
            .append(category)
            .append(establishment)
            .append(address)
            .append(city)
            .append(description)
            .append(infractionDate)
            .append(judgementDate)
            .append(importedDate).append(latitude).append(longitude).toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof InspectedPlace))
            return false;

        InspectedPlace rhs = (InspectedPlace) obj;
        return new EqualsBuilder()
                .append(amount, rhs.amount)
                .append(owner, rhs.owner)
                .append(category, rhs.category)
                .append(establishment, rhs.establishment)
                .append(address, rhs.address)
                .append(city, rhs.city)
                .append(description, rhs.description)
                .append(infractionDate, rhs.infractionDate)
                .append(judgementDate, rhs.judgementDate)
                .append(importedDate, rhs.importedDate)
                .append(latitude, rhs.latitude)
                .append(longitude, rhs.longitude)
                .isEquals();
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
