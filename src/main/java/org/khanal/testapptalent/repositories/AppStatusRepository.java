package org.khanal.testapptalent.repositories;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Domain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface AppStatusRepository extends CrudRepository<AppStatus, Long> {

    @Query("from Domain")
    Set<Domain> getDomains();

    Optional<AppStatus> findByCustomerId(long id);
}
