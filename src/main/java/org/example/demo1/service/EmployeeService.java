package org.example.demo1.service;

import org.example.demo1.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee findByEmail(String email);
}