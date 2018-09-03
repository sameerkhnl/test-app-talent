package org.khanal.testapptalent.resources;

import org.khanal.testapptalent.domains.Domain;

import java.util.List;

public class AppStatusResource {
    private String landingPage;
    private String settingsPage;
    private Boolean setupRequired;
    private String[] domainsUsed;

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

    public String[] getDomainsUsed() {
        return domainsUsed;
    }

    public void setDomainsUsed(String[] domainsUsed) {
        this.domainsUsed = domainsUsed;
    }
}
