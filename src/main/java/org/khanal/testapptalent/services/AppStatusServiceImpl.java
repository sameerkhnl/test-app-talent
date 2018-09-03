package org.khanal.testapptalent.services;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.AppStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppStatusServiceImpl implements AppStatusService{

    private final AppStatusRepository appStatusRepository;

    @Autowired
    public AppStatusServiceImpl(AppStatusRepository appStatusRepository) {
        this.appStatusRepository = appStatusRepository;
    }


    @Override
    public AppStatus getAppStatusFromCustomer(Customer customer) {
        return this.appStatusRepository.getByCustomer(customer).get();
    }
}
