package org.khanal.testapptalent.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.services.AppStatusService;
import org.khanal.testapptalent.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("mysql")
public class CustomerControllerITest {
    private CustomerService customerService;
    private AppStatusService appStatusService;
    private Long appStatusId;
    AppStatus retrieved;

    private final String token = "eyJhbGciOiJSUzUxMiJ9.cG9zdGdsb2JhbA.Afmj6V_RGvSF2jv4wgsywfZue-ZnF9pCG_6L8DF2_Pbt7bOIMMk4dYrqFc_Em1b1GeSPEkFR_owmb23W6qPtW5O0xfyMEOzn3EqAJPNFlmK5xknwcO13PV-YakKqzI9t7D8Rh6xOPTBLGwcuA9DHa5369ClUwu7KK0glLUQonEgUxBodR7VcvdT1T_xK1LiW_h-Y4Jv0Vhwhgxgt2jhnecpahfxekFvcfM9rKC6-5Za5ypXGmQf4F00ME76s44BFqvSbbFdiAebr6uRCWb8Ixgs8O8Vyy7obKdq0z1oxpbEOccAyCwcMofeJ0RB2KS913raEtyWrvGzsS0A4zuSNxA";

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public void setCustomerService(CustomerService customerService, AppStatusService appStatusService) {
        this.customerService = customerService;
        this.appStatusService = appStatusService;
    }

    @Before
    public void setup() {
        this.headers.set("Authorization", "Bearer " + token);
        AppStatus appStatus = new AppStatus();
        appStatus.setLandingPage("landingpage.com");
        appStatus.setSetupRequired(true);
        appStatus.setSettingsPage("settingspageeeee.com");
        retrieved = this.appStatusService.saveAppstatus(appStatus);
        this.appStatusId = retrieved.getId();
    }

    @Test
    public void getCustomer() {
        HttpEntity<Customer> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(createURLWithPort("/tenants/{acmesandbox}"), HttpMethod.GET, entity, Customer.class, "acmesandbox");
        //ResponseEntity<Customer> response = restTemplate.getForEntity("http://localhost:" + port + "/tenants/{tenant}", Customer.class, "acmesandbox");
        assertEquals( "fred@gmail.com", response.getBody().getContactEmail());
    }

    @Test

    public void addCustomer() {
        Customer customer = new Customer();
        customer.setShortCode("sameerkhnl");
        customer.setName("Sameer Khanal");
        customer.setContactEmail("khanal.sam91@gmail.com");
        customer.setType("sandbox");
        customer.setCountry("Np");
        customer.setAppStatus(retrieved);

        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(createURLWithPort("/tenants"), HttpMethod.POST, entity, Customer.class);
        assertEquals("Np", response.getBody().getCountry());
    }





    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }
}