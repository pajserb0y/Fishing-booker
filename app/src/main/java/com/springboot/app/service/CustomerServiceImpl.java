package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.Role;
import com.springboot.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleService roleService;

    public CustomerServiceImpl(CustomerRepository customerRepository, RoleService roleService) {
        this.customerRepository = customerRepository;
        this.roleService = roleService;
    }

    public Customer saveCustomer(Customer customer) {
        List<Role> roles = roleService.findByName("ROLE_CUSTOMER");
        customer.setRoles(roles);
        customerRepository.save(customer);
        return customer;
    }

    public Customer findByHashCode(String hashCode) {
        return customerRepository.findByHashCode(hashCode);
    }
}
