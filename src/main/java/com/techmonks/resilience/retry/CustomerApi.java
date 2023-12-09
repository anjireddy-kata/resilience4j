package com.techmonks.resilience.retry;

import com.techmonks.resilience.retry.exception.DataNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CustomerApi {
    private final WebClient.Builder webClientBuilder;

    private String customerExternalApiUrl = "https://657458bcf941bda3f2afa03f.mockapi.io/api/v1/customers";

    public CustomerApi(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Customer getCustomerById(String customerId) {
        Customer customer = webClientBuilder.build().method(HttpMethod.GET)
                .uri(customerExternalApiUrl + "/" + customerId)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
        if (customer.getCustomerId().equals("10")) {
            throw new DataNotFoundException("Customer not found with Id:{}" + customerId);
        }
        return customer;
    }
}
