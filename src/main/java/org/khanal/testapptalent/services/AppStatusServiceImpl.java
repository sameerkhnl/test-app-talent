package org.khanal.testapptalent.services;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.AppStatusRepository;
import org.khanal.testapptalent.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
        Customer customer = this.customerRepository.findByShortCode(code).get();
        return this.appStatusRepository.getByCustomer(customer).get();
    }

    @Override
    @Transactional
    public AppStatus getAppStatusByCustomerId(long id) {
        Customer customer = this.customerRepository.findById(id).get();
        return this.appStatusRepository.getByCustomer(customer).get();
    }
}
