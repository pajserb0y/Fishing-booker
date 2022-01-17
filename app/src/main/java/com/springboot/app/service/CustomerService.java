package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.dto.CustomerDTO;
import com.springboot.app.model.dto.EntityIdAndCustomerUsername;

import java.util.Collection;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer findByHashCode(String hashCode);
    Customer findByUsername(String username);
    Customer changeCustomer(CustomerDTO customerDTO);
    void setWantedToDelete(Integer id);
    Optional<Customer> findById(Integer id);
    Collection<String> findAllUsernames();

    void subscribeWeekendHouse(EntityIdAndCustomerUsername dto);

    void unsubscribeFishingLesson(EntityIdAndCustomerUsername dto);

    void unsubscribeBoat(EntityIdAndCustomerUsername dto);

    void unsubscribeWeekendHouse(EntityIdAndCustomerUsername dto);

    void subscribeFishingLesson(EntityIdAndCustomerUsername dto);

    void subscribeBoat(EntityIdAndCustomerUsername dto);
}
