package org.khanal.testapptalent.converters;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.repositories.DomainRepository;
import org.khanal.testapptalent.resources.AppStatusResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AppStatusToAppStatusResourceConverter implements Converter<AppStatus, AppStatusResource> {

    private DomainRepository domainRepository;

    @Autowired
    public AppStatusToAppStatusResourceConverter(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public AppStatusResource convert(AppStatus appStatus) {
        if(appStatus != null){
            AppStatusResource appStatusResource = new AppStatusResource();
            appStatusResource.setLandingPage(appStatus.getLandingPage());
            appStatusResource.setSettingsPage(appStatus.getSettingsPage());
            appStatusResource.setSetupRequired(appStatus.getSetupRequired());
            return appStatusResource;
        }
        return null;

    }
}
