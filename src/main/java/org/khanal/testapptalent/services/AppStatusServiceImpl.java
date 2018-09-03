package org.khanal.testapptalent.services;

import javassist.NotFoundException;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Domain;
import org.khanal.testapptalent.repositories.AppStatusRepository;
import org.khanal.testapptalent.repositories.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppStatusServiceImpl implements AppStatusService{

    private final AppStatusRepository appStatusRepository;
    private final DomainRepository domainRepository;

    @Autowired
    public AppStatusServiceImpl(AppStatusRepository appStatusRepository, DomainRepository domainRepository) {
        this.appStatusRepository = appStatusRepository;
        this.domainRepository = domainRepository;
    }

    @Override
    public AppStatus getAppStatusByCustomerId(long id) {
        return this.appStatusRepository.findByCustomerId(id).get();
    }

    @Override
    public List<Domain> getDomains() {
        return null;
    }
}
