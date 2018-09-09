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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    @GetMapping("/t/{tenant}/devs/{developer}/appStatus")
    public ResponseEntity<AppStatusResource> getAppStatus(@RequestParam(value = "setupRequired", required = false)Boolean setupRequired, @PathVariable String tenant, @PathVariable String developer) {
        //Customer customer = this.customerService.getCustomerByCode(tenantCode);
        if(setupRequired == null){
            AppStatus appStatus = this.appStatusService.getAppStatusByCustomerCode(tenant);

            AppStatusResource appStatusResource = this.appStatusToAppStatusResourceConverter.convert(appStatus);
            List<String> domainNames = this.domainService.getAppDomainNames();
            String[] domains = new String[domainNames.size()];
            domains = domainNames.toArray(domains);
            appStatusResource.setDomainsUsed(domains);
            return ResponseEntity.ok().body(appStatusResource);
        } else {
            Customer retrieved = this.customerService.getCustomerByCode(tenant);
            retrieved.getAppStatus().setSetupRequired(setupRequired);
            this.customerService.saveCustomer(retrieved);
            AppStatusResource appStatusResource = this.appStatusToAppStatusResourceConverter.convert(retrieved.getAppStatus());
            return ResponseEntity.ok().body(appStatusResource);
        }

    }

}
