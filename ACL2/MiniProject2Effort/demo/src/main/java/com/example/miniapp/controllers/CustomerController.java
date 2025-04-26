package com.example.miniapp.controllers;

import com.example.miniapp.models.Customer;
import com.example.miniapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.addCustomer(customer));
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id)); // returns 200 + null
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            Customer updated = customerService.updateCustomer(id, customer);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
            }
            return ResponseEntity.ok(updated);
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully.");
    }

    @GetMapping("/findByEmailDomain")
    public ResponseEntity<?> findCustomersByEmailDomain(@RequestParam String domain) {
        try {
            return ResponseEntity.ok(customerService.findCustomersByEmailDomain(domain));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/findByPhonePrefix")
    public ResponseEntity<?> findCustomersByPhonePrefix(@RequestParam String prefix) {
        try {
            return ResponseEntity.ok(customerService.findCustomersByPhonePrefix(prefix));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }
}