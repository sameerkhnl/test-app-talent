package org.khanal.testapptalent.domains;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="app_status")
public class AppStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="landing_page")
    private String landingPage;

    @Column(name="settings_page")
    private String settingsPage;

    @Column(name="setup_required")
    private Boolean setupRequired;

    @OneToMany(mappedBy = "appStatus")
    private Collection<Customer> customer;

    private String[] domains;

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public String getSettingsPage() {
        return settingsPage;
    }

    public void setSettingsPage(String settingsPage) {
        this.settingsPage = settingsPage;
    }

    public Boolean getSetupRequired() {
        return setupRequired;
    }

    public void setSetupRequired(Boolean setupRequired) {
        this.setupRequired = setupRequired;
    }


    public String[] getDomains() {
        return domains;
    }

    public void setDomains(String[] domains) {
        this.domains = domains;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(Collection<Customer> customer) {
        this.customer = customer;
    }
}
