package com.springboot.app.service;

import com.springboot.app.model.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer findByHashCode(String hashCode);
    Customer findByUsername(String username);
}
