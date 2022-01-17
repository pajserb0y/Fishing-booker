package com.springboot.app.model.dto;

import com.springboot.app.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

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
    @Size(min = 6)
    @NotEmpty(message = "Please fill out password")
    private String password;
    @NotEmpty(message = "Please fill out address")
    private String address;
    @NotEmpty(message = "Please fill out city")
    private String city;
    @NotEmpty(message = "Please fill out country")
    private String country;
    @Pattern(regexp = "[0-9]+", message = "Phone must contain only digits")
    @NotEmpty(message = "Please fill out phone")
    private String phone;
    private Integer penals;

    private Set<WeekendHouseDTO> subscribedWeekendHouses;
    private Set<BoatDTO> subscribedBoats;
    private Set<FishingLessonDTO> subscribedFishingLessons;


    public CustomerDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, Integer penals) {
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
        this.penals = penals;
    }

    public CustomerDTO() { }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.username = customer.getUsername();
        this.password = customer.getPassword();
        this.address = customer.getAddress();
        this.city = customer.getCity();
        this.country = customer.getCountry();
        this.phone = customer.getPhone();
        this.penals = customer.getPenals();

        Set<WeekendHouseDTO> houses = new HashSet<>();
        for (WeekendHouse w : customer.getSubscribedWeekendHouses())
            houses.add(new WeekendHouseDTO(w));
        this.subscribedWeekendHouses = houses;

        Set<BoatDTO> boats = new HashSet<>();
        for (Boat w : customer.getSubscribedBoats())
            boats.add(new BoatDTO(w));
        this.subscribedBoats = boats;

        Set<FishingLessonDTO> lessons = new HashSet<>();
        for (FishingLesson w : customer.getSubscribedFishingLessons())
            lessons.add(new FishingLessonDTO(w));
        this.subscribedFishingLessons = lessons;
    }

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

    public Integer getPenals() {
        return penals;
    }

    public void setPenals(Integer penals) {
        this.penals = penals;
    }

    public Set<WeekendHouseDTO> getSubscribedWeekendHouses() {
        return subscribedWeekendHouses;
    }

    public void setSubscribedWeekendHouses(Set<WeekendHouseDTO> subscribedWeekendHouses) {
        this.subscribedWeekendHouses = subscribedWeekendHouses;
    }

    public Set<BoatDTO> getSubscribedBoats() {
        return subscribedBoats;
    }

    public void setSubscribedBoats(Set<BoatDTO> subscribedBoats) {
        this.subscribedBoats = subscribedBoats;
    }

    public Set<FishingLessonDTO> getSubscribedFishingLessons() {
        return subscribedFishingLessons;
    }

    public void setSubscribedFishingLessons(Set<FishingLessonDTO> subscribedFishingLessons) {
        this.subscribedFishingLessons = subscribedFishingLessons;
    }
}
