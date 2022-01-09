package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.Role;
import com.springboot.app.model.dto.CustomerDTO;
import com.springboot.app.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
        customer.setRole(roles.get(0));
        customerRepository.save(customer);
        return customer;
    }

    public Customer findByHashCode(String hashCode) {
        return customerRepository.findByHashCode(hashCode);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer changeCustomer(CustomerDTO customerDTO) {
        Customer customer = findByUsername(customerDTO.getUsername());

        customer.setPhone(customerDTO.getPhone());
        customer.setCountry(customerDTO.getCountry());
        customer.setCity(customerDTO.getCity());
        customer.setAddress(customerDTO.getAddress());
        customer.setLastName(customerDTO.getLastName());
        customer.setFirstName(customerDTO.getFirstName());

        saveCustomer(customer);
        return customer;
    }

    @Override
    public void setWantedToDelete(Integer id) {
        Optional<Customer> customer = findById(id);
        if(customer.isPresent()) {
            customer.get().setWantDeleting(true);
            saveCustomer(customer.get());
        }
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Collection<String> findAllUsernames() {
        return customerRepository.findAllUsernames();
    }
}
