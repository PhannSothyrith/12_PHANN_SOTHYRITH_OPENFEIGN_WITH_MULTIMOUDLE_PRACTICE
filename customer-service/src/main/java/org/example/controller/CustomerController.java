package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Customer;
import org.example.model.request.CustomerRequest;
import org.example.model.response.ApiResponse;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.createCustomer(customerRequest);
        ApiResponse<Customer> apiResponse = ApiResponse.<Customer>builder()
                .message("Create customer successfully")
                .payload(customer)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
   public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers(){
        List<Customer> customer= customerService.getAllCustomer();
        ApiResponse<List<Customer>> apiResponse = ApiResponse.<List<Customer>>builder()
                .message("Get all customers successfully")
                .payload(customer)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("get-customer/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        ApiResponse<Customer> apiResponse = ApiResponse.<Customer>builder()
                .message("Get customer with id "+ id + " successfully")
                .payload(customer)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.updateCustomerById(id, customerRequest);
        ApiResponse<Customer> apiResponse = ApiResponse.<Customer>builder()
                .message("Update customer with id "+ id + " successfully")
                .payload(customer)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        ApiResponse<Customer> apiResponse = ApiResponse.<Customer>builder()
                .message("Delete customer with id "+ id + " successfully")
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
