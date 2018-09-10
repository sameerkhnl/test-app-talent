package org.khanal.testapptalent.services;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.khanal.testapptalent.domains.AppStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@ActiveProfiles("mysql")
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppStatusServiceImplITest {

    private AppStatusService appStatusService;
    private AppStatus appStatus = new AppStatus();

    @Autowired
    public void setAppStatusService(AppStatusService appStatusService) {
        this.appStatusService = appStatusService;
    }

    @Before
    public void setup() {
        this.appStatus.setSetupRequired(true);
        this.appStatus.setLandingPage("testLandingPage");
        this.appStatus.setSettingsPage("testSettingsPage");
    }

    @Test
    public void b_getAppStatusByCustomerCode() {
        AppStatus retrieved = this.appStatusService.getAppStatusByCustomerCode("acmesandbox");
        assertEquals("localhost:4200/setup", retrieved.getSettingsPage());
    }

    @Test
    public void c_getAppStatusByCustomerId() {
        AppStatus retrieved = this.appStatusService.getAppStatusByCustomerId(1);
        assertEquals("localhost:4200/setup", retrieved.getSettingsPage());
    }

    @Test
    public void a_saveAppstatus() {
        AppStatus retrieved = this.appStatusService.saveAppstatus(appStatus);
        assertEquals(appStatus.getLandingPage(), retrieved.getLandingPage());
    }
}