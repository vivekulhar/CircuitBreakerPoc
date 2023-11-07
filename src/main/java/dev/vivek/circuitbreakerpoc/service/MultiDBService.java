package dev.vivek.circuitbreakerpoc.service;

import dev.vivek.circuitbreakerpoc.db1.repo.EmpRepo1;
import dev.vivek.circuitbreakerpoc.db2.entity.Employees;
import dev.vivek.circuitbreakerpoc.db2.repo.EmpRepo2;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiDBService {

    private CircuitBreaker circuitBreaker;
    @Autowired
    private EmpRepo1 empRepo1;
    @Autowired
    private EmpRepo2 empRepo2;

    public MultiDBService(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("abc");
    }

    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name="abc", fallbackMethod = "fallbackMethodForDb2Data")
    public Iterable<?> getEmpData(String flag) {
        System.out.println("Under main method ::"+circuitBreaker.getState().name());
        System.out.println("called regular method.....");
        if("fail".equals(flag)) {
            throw new RuntimeException("db1 is down");
        }

        Iterable<dev.vivek.circuitbreakerpoc.db1.entity.Employees> db1EmpData = empRepo1.findAll();
        return db1EmpData;
    }
// ? data can come from any database
    public Iterable<?> fallbackMethodForDb2Data(Throwable t) {
        System.out.println("Under fallback method ::"+circuitBreaker.getState().name());
        System.out.println("called fallback method.....");
        Iterable<dev.vivek.circuitbreakerpoc.db2.entity.Employees> db2EmpData = empRepo2.findAll();
        return db2EmpData;
    }
}
