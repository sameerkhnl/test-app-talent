package org.khanal.testapptalent.controllers;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.services.AppStatusService;
import org.khanal.testapptalent.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/landingPage")
public class LandingPageController {
    private final AppStatusService appStatusService;
    private final CustomerService customerService;

    public LandingPageController(AppStatusService appStatusService, CustomerService customerService) {
        this.appStatusService = appStatusService;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> getLandingPage(final Principal principal) {
        AppStatus appStatus;
        if(principal != null){
            String name = principal.getName();
            appStatus = this.appStatusService.getAppStatusByCustomerCode(name);
        } else {
            appStatus = this.appStatusService.getAppStatusByCustomerId(1);
        }

        return ResponseEntity.ok().body(appStatus.getLandingPage());
    }
}
