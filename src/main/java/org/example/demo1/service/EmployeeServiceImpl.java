package org.example.demo1.service;

import org.example.demo1.model.Employee;
import org.example.demo1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        System.out.println("Saved to repository: " + saved);
        return saved;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmail(String email) {
        System.out.println("Finding employee by email: " + email);
        return employeeRepository.findByEmail(email).orElse(null);
    }
}