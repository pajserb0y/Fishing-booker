package com.springboot.app.repository;

import com.springboot.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByHashCode(String hashCode);
    Customer findByUsername(String username);
}