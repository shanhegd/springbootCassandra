package com.usbank.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.usbank.app.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
