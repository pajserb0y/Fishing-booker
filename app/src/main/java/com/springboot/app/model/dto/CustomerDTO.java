package com.springboot.app.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CustomerDTO {
    private int id;
    @NotEmpty(message = "Please fill out first name")
    private String firstName;
    @NotEmpty(message = "Please fill out last name")
    private String lastName;
    @Email(message = "Email is in wrong format")
    @NotBlank(message = "Please fill out email")
    private String email;
    @NotEmpty(message = "Please fill out username")
    private String username;
    @NotEmpty(message = "Please fill out password")
    private String password;
    @NotEmpty(message = "Please fill out address")
    private String address;
    @NotEmpty(message = "Please fill out city")
    private String city;
    @NotEmpty(message = "Please fill out country")
    private String country;
    @NotEmpty(message = "Please fill out phone")
    private String phone;

    public CustomerDTO(int id, String firstName, String lastName, String email, String password, String address, String city, String country, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public CustomerDTO() { }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}