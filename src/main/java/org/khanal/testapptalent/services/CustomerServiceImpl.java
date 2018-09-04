package org.khanal.testapptalent.services;

import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerByCode(String code) {
        return this.customerRepository.findByShortCode(code).get();
    }
}
