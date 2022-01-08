package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.dto.CustomerDTO;

import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer findByHashCode(String hashCode);
    Customer findByUsername(String username);
    Customer changeCustomer(CustomerDTO customerDTO);

    void setWantedToDelete(Integer id);

    Optional<Customer> findById(Integer id);
}
