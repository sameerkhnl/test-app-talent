package org.khanal.testapptalent.controllers;

import org.khanal.testapptalent.services.AppStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/landingPage")
public class LandingPageController {
    private final AppStatusService appStatusService;

    public LandingPageController(AppStatusService appStatusService) {
        this.appStatusService = appStatusService;
    }

    @GetMapping
    public ResponseEntity<?> getLandingPage() {
        return ResponseEntity.ok().body(appStatusService.getAppStatus().getLandingPage());
    }
}
