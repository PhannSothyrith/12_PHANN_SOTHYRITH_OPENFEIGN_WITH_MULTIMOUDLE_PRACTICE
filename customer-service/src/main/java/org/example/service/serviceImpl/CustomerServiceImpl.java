package org.example.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.model.Customer;
import org.example.model.request.CustomerRequest;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRequest.toEntity();
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer not found"));
    }

    @Override
    public Customer updateCustomerById(Long id, CustomerRequest customerRequest) {
        if (customerRepository.findById(id).isEmpty()){
            throw  new NotFoundException("Customer not found");
        }
       Customer customer = customerRequest.toEntity(id);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (customerRepository.findById(id).isEmpty()){
            throw new NotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
