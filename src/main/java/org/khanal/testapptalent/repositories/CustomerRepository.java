package org.khanal.testapptalent.repositories;

import org.khanal.testapptalent.domains.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "tenant", path = "tenant")
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    @RestResource(exported = false)
    Optional<Customer> findById(Long aLong);

    @Override
    @RestResource(exported = false)
    boolean existsById(Long aLong);

    @Override
    @RestResource(exported = false)
    Iterable<Customer> findAllById(Iterable<Long> iterable);

    @Override
    @RestResource(exported = false)
    long count();

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(Customer customer);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Customer> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    Optional<Customer> findByShortCode(String code);
}
