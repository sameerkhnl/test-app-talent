package org.khanal.testapptalent.services;

import org.khanal.testapptalent.domains.Domain;
import org.khanal.testapptalent.repositories.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DomainServiceImpl implements DomainService{
    private final DomainRepository domainRepository;

    public DomainServiceImpl(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public List<String> getAppDomainNames() {
        List<String> domainNames = new ArrayList<>();
        this.domainRepository.findAll().forEach(d -> domainNames.add(d.getDomainName()));
        return domainNames;
    }
}
