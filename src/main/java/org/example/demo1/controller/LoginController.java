package org.example.demo1.controller;

import org.example.demo1.model.Employee;
import org.example.demo1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Received login request: email=" + loginRequest.getEmail());
            Employee employee = employeeService.findByEmail(loginRequest.getEmail());
            if (employee == null) {
                System.out.println("Login failed: Employee not found for email=" + loginRequest.getEmail());
                return ResponseEntity.status(401).body("Invalid email or password");
            }
            if (!employee.getPassword().equals(loginRequest.getPassword())) {
                System.out.println("Login failed: Password mismatch for email=" + loginRequest.getEmail());
                return ResponseEntity.status(401).body("Invalid email or password");
            }
            System.out.println("Login successful for email=" + loginRequest.getEmail());
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }
}