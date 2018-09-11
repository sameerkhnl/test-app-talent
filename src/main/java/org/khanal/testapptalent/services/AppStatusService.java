package org.khanal.testapptalent.services;

import javassist.NotFoundException;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.domains.Domain;

import java.util.List;

public interface AppStatusService {


    AppStatus saveAppstatus(AppStatus appStatus);
    AppStatus getStatusById(Long id);

}
