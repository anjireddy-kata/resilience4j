package com.techmonks.resilience.retry;

import com.techmonks.resilience.retry.exception.DataNotFoundException;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    private final CustomerApi customerApi;

    private static final String GET_CUSTOMER_DETAILS_BY_CUSTOMER_ID = "getCustomerDetailsByCustomerId";

    private final RetryRegistry retryRegistry;

    public CustomerService(CustomerApi customerApi, RetryRegistry retryRegistry) {
        this.customerApi = customerApi;
        this.retryRegistry = retryRegistry;
    }

    @PostConstruct
    public void postConstruct() {
        io.github.resilience4j.retry.Retry.EventPublisher eventPublisher =
                retryRegistry.retry(GET_CUSTOMER_DETAILS_BY_CUSTOMER_ID).getEventPublisher();
        eventPublisher.onEvent(event -> log.info("Get Customer Details by Customer Id - On Event. Details: " + event));
        eventPublisher.onSuccess(event -> log.info("Get Customer Details by Customer Id - On Success. Details: " + event));
        eventPublisher.onError(event -> log.info("Get Customer Details by Customer Id - On Error. Details: " + event));
        eventPublisher.onRetry(event -> log.info("Get Customer Details by Customer Id - On Retry. Details: " + event));
        eventPublisher.onIgnoredError(event -> log.info("Get Customer Details by Customer Id - On Ignored Error. Details: " + event));
    }

    @Retry(name = GET_CUSTOMER_DETAILS_BY_CUSTOMER_ID, fallbackMethod = "getCustomerDetailsByCustomerIdFallback")
    public Customer getCustomerDetailsByCustomerId(String customerId) {
        log.info("Retrieve customer Information");
        return this.customerApi.getCustomerById(customerId);
    }

    public Customer getCustomerDetailsByCustomerIdFallback(String customerId, DataNotFoundException dataNotFoundException) {
        log.info("Fallback triggered");
        log.info("Exception " + dataNotFoundException.getMessage());
        return new Customer("default", "default");
    }
}
