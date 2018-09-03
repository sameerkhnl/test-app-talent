package org.khanal.testapptalent.repositories;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AppStatusRepository extends CrudRepository<AppStatus, Long> {

    Optional<AppStatus> getByCustomer(Customer customer);
}
