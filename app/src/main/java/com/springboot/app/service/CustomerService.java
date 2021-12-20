package com.springboot.app.service;

import com.springboot.app.model.Customer;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
    public Customer findByHashCode(String hashCode);
}
