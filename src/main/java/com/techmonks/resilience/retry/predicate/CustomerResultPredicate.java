package com.techmonks.resilience.retry.predicate;

import com.techmonks.resilience.retry.Customer;

import java.util.function.Predicate;

public class CustomerResultPredicate implements Predicate<Customer> {
    @Override
    public boolean test(Customer customer) {
        //TODO: Custom rules goes here
        System.out.println("Result Predicate");
        return customer.getCustomerId().equals("1");
    }
}
