package org.example.service;

import org.example.model.Customer;
import org.example.model.request.CustomerRequest;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerRequest customerRequest);
    List<Customer> getAllCustomer();
    Customer getCustomerById(Long id);
    Customer updateCustomerById(Long id, CustomerRequest customerRequest);
    void deleteCustomerById(Long id);
}
