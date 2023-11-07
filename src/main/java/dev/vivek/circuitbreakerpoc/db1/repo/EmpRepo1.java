package dev.vivek.circuitbreakerpoc.db1.repo;

import dev.vivek.circuitbreakerpoc.db1.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EmpRepo1 extends JpaRepository<Employees, Serializable> {
}
