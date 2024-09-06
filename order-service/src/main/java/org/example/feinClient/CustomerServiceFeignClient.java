package org.example.feinClient;

import org.example.model.response.ApiResponse;
import org.example.model.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",url ="http://localhost:8081/api/v1/customers")
public interface CustomerServiceFeignClient {
    @GetMapping("get-customer/{id}")
    ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long id);


}
