package org.example.demo1.controller;

import jakarta.transaction.Transactional;
import org.example.demo1.model.Employee;
import org.example.demo1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @Transactional
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        System.out.println("Received raw employee object: " + employee);
        if (employee == null) {
            System.out.println("Employee object is null!");
            return ResponseEntity.badRequest().body(null);
        }
        System.out.println("Received data - firstName: " + (employee.getFirstName() != null ? employee.getFirstName() : "null")
                + ", email: " + (employee.getEmail() != null ? employee.getEmail() : "null"));
        System.out.println("Saving employee: " + employee);
        try {
            Employee saved = employeeService.saveEmployee(employee);
            System.out.println("Saved employee: " + saved);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            System.out.println("Error saving employee: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}