package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public Customer findByHashCode(String hashCode) {
        return customerRepository.findByHashCode(hashCode);
    }
}
