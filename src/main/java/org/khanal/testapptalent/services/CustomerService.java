package org.khanal.testapptalent.services;

import org.khanal.testapptalent.domains.AppStatus;
import org.khanal.testapptalent.domains.Customer;

public interface CustomerService {
    Customer getCustomerByCode(String code);
    Customer saveCustomer(Customer customer);
    Customer makeCustomerInactive(String code);
}
