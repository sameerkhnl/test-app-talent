package org.khanal.testapptalent.services;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private AppStatusService appStatusService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AppStatusService appStatusService) {
        this.customerRepository = customerRepository;
        this.appStatusService = appStatusService;
    }

    @Override
    public Customer getCustomerByCode(String code) {
        return this.customerRepository.findByShortCode(code).get();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        //if no appStatus then set default appStatus with ID1
        if (customer.getAppStatus() == null) {
            AppStatus appStatus = this.appStatusService.getStatusById(1L);
            this.appStatusService.saveAppstatus(appStatus);
            customer.setAppStatus(appStatus);
        }
        Customer c = this.customerRepository.save(customer);
        return c;
    }

    @Override
    public Customer makeCustomerInactive(String code) {
        Customer customer = this.getCustomerByCode(code);
        if (customer.getAppStatus() != null) {
            customer.getAppStatus().setSetupRequired(true);
        }
        customer.setActive(false);
        return customer;
    }
}
