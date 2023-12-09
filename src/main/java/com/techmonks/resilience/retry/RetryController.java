package com.techmonks.resilience.retry;

import com.techmonks.resilience.retry.exception.DataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {

    private final CustomerService customerService;

    public RetryController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) throws InterruptedException, DataNotFoundException {
        return ResponseEntity.ok(this.customerService.getCustomerDetailsByCustomerId(customerId));
    }
}
