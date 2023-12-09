package com.techmonks.resilience;

import com.techmonks.resilience.retry.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResilienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResilienceApplication.class, args);
    }

    private static void testRetry() {

    }

}
