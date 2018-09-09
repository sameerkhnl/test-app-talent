package org.khanal.testapptalent.controllers;

import org.khanal.testapptalent.domains.Customer;
import org.khanal.testapptalent.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("/tenants")
@CrossOrigin
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path = "/tenants", method = RequestMethod.POST)
    public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer) {
        try {
            Customer retrieved = this.customerService.saveCustomer(customer);
            return ResponseEntity.created(new URI("/tenants/" + retrieved.getShortCode())).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/tenants/{tenant}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("tenant") String shortCode){
        Customer customer = this.customerService.getCustomerByCode(shortCode);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PutMapping("/tenants/{tenant}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("tenant") String shortCode, @RequestBody Customer customer){
        Customer retrieved = this.customerService.getCustomerByCode(shortCode);
        customer.setId(retrieved.getId());
        customer.setAppStatus(retrieved.getAppStatus());

        Customer updatedCustomer = this.customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(retrieved);
    }

}
