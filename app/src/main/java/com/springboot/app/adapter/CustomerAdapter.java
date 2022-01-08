package com.springboot.app.adapter;

import com.springboot.app.model.Customer;
import com.springboot.app.model.dto.CustomerDTO;

public class CustomerAdapter {

    public static Customer DtoToCustomerWithoutHashingPassword(CustomerDTO customerDto ) {
        Customer customer = new Customer();

//        customer.setId(customerDto.getId());
//        customer.setFirstName(customerDto.getId());
//        customer.setLastName(customerDto.getId());
//        customer.setEmail(customerDto.getId());
//        customer.setUsername(customerDto.getId());
//        customer.setPassword(customerDto.getId());
//        customer.setAddress(customerDto.getId());
//        customer.setCity(customerDto.getId());
//        customer.setCountry(customerDto.getId());
//        customer.setPhone(customerDto.getId());
//        customer.setHashCode(customerDto.getId());

        return customer;
    }
}
