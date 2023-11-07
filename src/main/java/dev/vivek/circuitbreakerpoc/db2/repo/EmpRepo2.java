package dev.vivek.circuitbreakerpoc.db2.repo;

import dev.vivek.circuitbreakerpoc.db2.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EmpRepo2 extends JpaRepository<Employees, Serializable> {
}
