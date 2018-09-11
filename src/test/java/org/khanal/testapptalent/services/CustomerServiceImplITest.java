package org.khanal.testapptalent.services;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("mysql")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceImplITest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private Customer customer = new Customer();

    @Autowired
    public void setCustomerService(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @Before
    public void setup() {
        List<Customer> customerList = new ArrayList<>();
        this.customerRepository.findAll().forEach(c -> customerList.add(c));
        List<Customer> listToBeDeleted = customerList.stream().filter(c -> c.getId() > 1).collect(Collectors.toList());
        //delete the test data before every test
        this.customerRepository.deleteAll(listToBeDeleted);

        customer.setShortCode("sameerkhnl");
        customer.setName("Sameer Khanal");
        customer.setContactEmail("khanal.sam91@gmail.com");
        customer.setType("sandbox");
        customer.setCountry("Np");
    }

    @Test
    public void a_saveCustomer() {
        Customer retrieved = this.customerService.saveCustomer(this.customer);
        assertTrue(retrieved.getId() > 0);
        assertEquals(true, retrieved.isActive());

    }

    @Test
    public void b_getCustomerByCode() {
        this.customerService.saveCustomer(customer);
        Customer retrieved = this.customerService.getCustomerByCode("sameerkhnl");
        assertEquals("khanal.sam91@gmail.com", retrieved.getContactEmail());
    }

    @Test
    public void c_makeCustomerInactive() {
        this.customerService.saveCustomer(customer);

        assertEquals(true, this.customer.isActive());
        Customer retrieved = this.customerService.makeCustomerInactive("sameerkhnl");
        assertEquals(false, retrieved.isActive());
    }
}