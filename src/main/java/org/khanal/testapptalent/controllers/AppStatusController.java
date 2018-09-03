package org.khanal.testapptalent.controllers;

import org.khanal.testapptalent.converters.AppStatusToAppStatusResourceConverter;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Domain;
import org.khanal.testapptalent.resources.AppStatusResource;
import org.khanal.testapptalent.services.AppStatusService;
import org.khanal.testapptalent.services.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appstatus")
public class AppStatusController {
    private final AppStatusService appStatusService;
    private final DomainService domainService;
    private final AppStatusToAppStatusResourceConverter appStatusToAppStatusResourceConverter;

    @Autowired
    public AppStatusController(AppStatusService appStatusService, DomainService domainService, AppStatusToAppStatusResourceConverter appStatusToAppStatusResourceConverter) {
        this.appStatusService = appStatusService;
        this.domainService = domainService;
        this.appStatusToAppStatusResourceConverter = appStatusToAppStatusResourceConverter;
    }

    @GetMapping
    public ResponseEntity<AppStatusResource> getAppStatus() {
        AppStatus appStatus = this.appStatusService.getAppStatus();
        AppStatusResource appStatusResource = this.appStatusToAppStatusResourceConverter.convert(appStatus);
        List<String> domainNames = this.domainService.getAppDomainNames();
        String[] domains = new String[domainNames.size()];
        domains = domainNames.toArray(domains);
        appStatusResource.setDomainsUsed(domains);
        return ResponseEntity.ok().body(appStatusResource);
    }
}
