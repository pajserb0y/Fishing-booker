package com.springboot.app.adapter;

import com.springboot.app.model.Customer;
import com.springboot.app.model.dto.CustomerDTO;

public class CustomerAdapter {

    public static Customer DtoToCustomerWithoutHashingPassword(CustomerDTO customerDto ) {
        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setCountry(customerDto.getCountry());
        customer.setPhone(customerDto.getPhone());
        customer.setHashCode(customerDto.getAddress());

        return customer;
    }
}
