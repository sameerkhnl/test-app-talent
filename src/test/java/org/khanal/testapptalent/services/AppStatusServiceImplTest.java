package org.khanal.testapptalent.services;

import org.junit.Before;
import org.junit.Test;
import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.repositories.AppStatusRepository;
import org.khanal.testapptalent.repositories.CustomerRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

public class AppStatusServiceImplTest {
    private AppStatusService appStatusService;

    @Mock
    private AppStatusRepository appStatusRepository;

    @Mock
    private CustomerRepository customerRepository;

    AppStatus appStatus;

    Customer customer;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        appStatusService = new AppStatusServiceImpl(appStatusRepository, customerRepository);
        appStatus = new AppStatus();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(appStatusRepository);
        assertNotNull(customerRepository);
    }


    @Test
    public void saveAppstatus() {
        this.appStatusService.saveAppstatus(appStatus);
        verify(appStatusRepository, times(1)).save(appStatus);
    }
}