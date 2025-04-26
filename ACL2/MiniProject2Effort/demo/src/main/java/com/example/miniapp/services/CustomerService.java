package com.example.miniapp.services;
import com.example.miniapp.models.Customer;
import com.example.miniapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new InvalidDataAccessApiUsageException("Customer must not be null");
        }
        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().length() < 8) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        if (id == null) return null;
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        if (updatedCustomer == null) {
            throw new InvalidDataAccessApiUsageException("Customer must not be null");
        }
        if (!customerRepository.existsById(id)) {
            return null;
        }

        updatedCustomer.setId(id);
        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        if (id != null && customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
    }

    public List<Customer> findCustomersByEmailDomain(String domain) {
        if (domain == null || !domain.contains(".")) {
            throw new IllegalArgumentException("Invalid domain");
        }
        return customerRepository.findByEmailEndingWith(domain);
    }

    public List<Customer> findCustomersByPhonePrefix(String prefix) {
        if (prefix == null || prefix.length() < 3) {
            throw new IllegalArgumentException("Invalid phone prefix");
        }
        return customerRepository.findByPhoneNumberStartingWith(prefix);
    }
}