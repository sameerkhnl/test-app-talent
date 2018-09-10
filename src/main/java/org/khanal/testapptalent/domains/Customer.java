package org.khanal.testapptalent.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name="name")
    private String name;

    @NotNull
    @NotEmpty
    @Column(name="contact_email")
    private String contactEmail;

    @NotNull
    @NotEmpty
    @Column(name="short_code")
    private String shortCode;

    @NotNull
    @Column(name="type")
    private String type;

    @Column(name="country")
    private String country;

    @Column(name="timezone")
    private String timeZone;

    @Column(name="logo_url")
    private String logoUrl;

    @Column(name="privacy_policy_url")
    private String privacyPolicyUrl;

    @Column(name="active")
    private boolean active = true;

    @JsonIgnore
    @Column(name = "created_on")
    private Date createdOn;

    @JsonIgnore
    @Column(name="updated_on")
    private Date updatedOn;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "app_status_id")
    private AppStatus appStatus;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public AppStatus getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(AppStatus appStatus) {
        this.appStatus = appStatus;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        if(createdOn == null){
            createdOn = new Date();
        }
        updatedOn = new Date();
    }
}

