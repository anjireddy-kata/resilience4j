package com.techmonks.resilience.retry.predicate;

import com.techmonks.resilience.retry.exception.DataNotFoundException;

import java.util.function.Predicate;

public class ExceptionPredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        System.out.println("Exception predicate triggered.");
        return throwable instanceof DataNotFoundException;
    }
}
