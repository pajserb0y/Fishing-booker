package com.springboot.app.model.dto;

import com.springboot.app.model.User;
import com.springboot.app.model.WeekendHouseOwner;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserDTO {
    protected int id;
    @NotEmpty(message = "Please fill out first name")
    protected String firstName;
    @NotEmpty(message = "Please fill out last name")
    protected String lastName;
    @Email(message = "Email is in wrong format")
    @NotBlank(message = "Please fill out email")
    protected String email;
    @NotEmpty(message = "Please fill out username")
    protected String username;
    @NotEmpty(message = "Please fill out password")
    protected String password;
    @NotEmpty(message = "Please fill out address")
    protected String address;
    @NotEmpty(message = "Please fill out city")
    protected String city;
    @NotEmpty(message = "Please fill out country")
    protected String country;
    @NotEmpty(message = "Please fill out phone")
    protected String phone;

    public UserDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }
    public UserDTO(User user)
    {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.phone = user.getPhone();
    }
    UserDTO()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
