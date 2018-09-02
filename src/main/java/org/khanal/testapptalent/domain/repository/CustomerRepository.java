package org.khanal.testapptalent.domain.repository;

import org.khanal.testapptalent.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tenant", path = "tenant")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
