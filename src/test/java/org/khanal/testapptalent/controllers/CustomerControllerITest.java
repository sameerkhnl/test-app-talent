package org.khanal.testapptalent.controllers;

import org.hibernate.annotations.SQLDelete;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.services.AppStatusService;
import org.khanal.testapptalent.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
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

    private String token;

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public void setCustomerService(CustomerService customerService, AppStatusService appStatusService, @Value("${auth.token.value}") String token) {
        this.customerService = customerService;
        this.appStatusService = appStatusService;
        this.token = token;
        System.out.println(token);
    }

    @Before
    public void setup() {
        this.headers.set("Authorization", "Bearer " + token);
        System.out.println(token);
    }

    @Test
    public void getCustomer() {
        HttpEntity<Customer> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(createURLWithPort("/tenants/{acmesandbox}"), HttpMethod.GET, entity, Customer.class, "acmesandbox");
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

        this.customerService.saveCustomer(customer);

        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(createURLWithPort("/tenants"), HttpMethod.POST, entity, Customer.class);
        assertEquals("Np", response.getBody().getCountry());
    }


    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }
}