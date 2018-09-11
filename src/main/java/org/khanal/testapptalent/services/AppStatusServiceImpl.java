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
    public AppStatus saveAppstatus(AppStatus appStatus) {
        return this.appStatusRepository.save(appStatus);

    }

    @Override
    public AppStatus getStatusById(Long id) {
        return this.appStatusRepository.findById(id).get();
    }


}
