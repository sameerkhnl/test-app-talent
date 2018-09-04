package org.khanal.testapptalent.controllers;

import org.khanal.testapptalent.converters.AppStatusToAppStatusResourceConverter;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.resources.AppStatusResource;
import org.khanal.testapptalent.services.AppStatusService;
import org.khanal.testapptalent.services.CustomerService;
import org.khanal.testapptalent.services.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/appStatus")
public class AppStatusController {
    private final AppStatusService appStatusService;
    private final DomainService domainService;
    private final CustomerService customerService;

    private final AppStatusToAppStatusResourceConverter appStatusToAppStatusResourceConverter;

    @Autowired
    public AppStatusController(AppStatusService appStatusService, DomainService domainService, CustomerService customerService, AppStatusToAppStatusResourceConverter appStatusToAppStatusResourceConverter) {
        this.appStatusService = appStatusService;
        this.domainService = domainService;
        this.customerService = customerService;
        this.appStatusToAppStatusResourceConverter = appStatusToAppStatusResourceConverter;
    }

    @GetMapping
    public ResponseEntity<AppStatusResource> getAppStatus(final Principal principal) {
        AppStatus appStatus;
        if(principal != null){
             String name = principal.getName();
             appStatus = this.appStatusService.getAppStatusByCustomerCode(name);
        } else {
            //for testing
            appStatus = this.appStatusService.getAppStatusByCustomerId(1);
        }
        AppStatusResource appStatusResource = this.appStatusToAppStatusResourceConverter.convert(appStatus);
        List<String> domainNames = this.domainService.getAppDomainNames();
        String[] domains = new String[domainNames.size()];
        domains = domainNames.toArray(domains);
        appStatusResource.setDomainsUsed(domains);
        return ResponseEntity.ok().body(appStatusResource);
    }
}
