package org.khanal.testapptalent.services;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.khanal.testapptalent.domains.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("mysql")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceImplITest {

    private CustomerService customerService;
    private Customer customer = new Customer();

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Before
    public void setup() {
        customer.setShortCode("sameerkhnl");
        customer.setName("Sameer Khanal");
        customer.setContactEmail("khanal.sam91@gmail.com");
        customer.setType("sandbox");
        customer.setCountry("Np");
    }

    @Test
    public void a_saveCustomer() {
        Customer retrueved = this.customerService.saveCustomer(this.customer);
        assertTrue(retrueved.getId() > 0);
        assertEquals(true, retrueved.isActive());

    }

    @Test
    public void b_getCustomerByCode() {
        Customer retrieved = this.customerService.getCustomerByCode("sameerkhnl");
        assertEquals("khanal.sam91@gmail.com", retrieved.getContactEmail());
    }

    @Test
    public void c_makeCustomerInactive() {
        assertEquals(true, this.customer.isActive());
        Customer retrieved = this.customerService.makeCustomerInactive("sameerkhnl");
        assertEquals(false, retrieved.isActive());
    }
}