package com.example.miniapp.repositories;

import com.example.miniapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmailEndingWith(String domain);
    List<Customer> findByPhoneNumberStartingWith(String prefix);
}
