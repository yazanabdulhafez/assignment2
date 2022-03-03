package com.brightMinds.assignment2.Repositories;

import com.brightMinds.assignment2.Models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    Employee findById(long id);
}
