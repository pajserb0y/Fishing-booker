package com.springboot.app.repository;

import com.springboot.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByHashCode(String hashCode);
    Customer findByUsername(String username);

    @Query("SELECT c.username FROM Customer c")
    Collection<String> findAllUsernames();
}
