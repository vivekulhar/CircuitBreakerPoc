package dev.vivek.circuitbreakerpoc.db1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employees   {
    @Id
    @Column(name = "EMP_ID")
    private Integer empId;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "EMP_SALARY")
    private String empSalary;
}
