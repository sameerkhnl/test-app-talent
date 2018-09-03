package org.khanal.testapptalent.repositories;

import org.khanal.testapptalent.domains.Domain;
import org.springframework.data.repository.CrudRepository;


public interface DomainRepository extends CrudRepository<Domain, Long> {
}
