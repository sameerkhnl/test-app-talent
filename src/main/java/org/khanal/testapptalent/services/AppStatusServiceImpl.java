package org.khanal.testapptalent.services;

import org.khanal.testapptalent.controllers.CustomerNotFoundException;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.AppStatusRepository;
import org.khanal.testapptalent.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AppStatusServiceImpl implements AppStatusService{

    private final AppStatusRepository appStatusRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AppStatusServiceImpl(AppStatusRepository appStatusRepository, CustomerRepository customerRepository) {
        this.appStatusRepository = appStatusRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public AppStatus getAppStatusByCustomerCode(String code) {
        Optional<Customer> customerOptional = this.customerRepository.findByShortCode(code);
        if(customerOptional.isPresent()){
            Optional<AppStatus> appStatusOptional = this.appStatusRepository.getByCustomer(customerOptional.get());
            return appStatusOptional.get();
        } else {
            throw new CustomerNotFoundException("Could not find tenant with the code");
        }
    }

    @Override
    @Transactional
    public AppStatus getAppStatusByCustomerId(long id) {
        Customer customer = this.customerRepository.findById(id).get();
        return this.appStatusRepository.getByCustomer(customer).get();
    }
}
