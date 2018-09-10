package org.khanal.testapptalent.services;

import org.junit.Before;
import org.junit.Test;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.CustomerRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    private Customer customer = new Customer();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
//        customer = new Customer();
//        customer.setShortCode("sameerkhnl");
//        customer.setName("Sameer Khanal");
//        customer.setContactEmail("khanal.sam91@gmail.com");
//        customer.setType("sandbox");
//        customer.setCountry("Np");
    }


    @Test
    public void saveCustomer() {
        this.customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
    }

}