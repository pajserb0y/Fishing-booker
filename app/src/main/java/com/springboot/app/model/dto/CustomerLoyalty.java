package com.springboot.app.model.dto;

import com.springboot.app.model.Customer;

public class CustomerLoyalty {
    private CustomerDTO customer;
    private Integer points;
    private String category;


    public CustomerLoyalty() {
    }

    public CustomerLoyalty(Customer customer) {
        this.customer = new CustomerDTO(customer);
        this.points = (int)(Math.random()*100);
        if (this.points < 30)
            this.category = "Bronze";
        else if (this.points < 70)
            this.category = "Silver";
        else
            this.category = "Gold";
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
