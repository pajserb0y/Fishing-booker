package com.springboot.app.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "weekend_house_reservation")
public class WeekendHouseReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please fill out starting date")
    private Date startDateTime;

    @NotEmpty(message = "Please fill out duration of stay")
    private Date endDateTime;

    @NotEmpty(message = "Please fill out number of people")
    private Integer peopleNumber;

    private Date startSpecialOffer = new Date();

    private Date endSpecialOffer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "weekend_house_reservation_services",
            joinColumns = @JoinColumn(name = "weekend_house_reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<AdditionalService> services = new HashSet<>();

    @NotEmpty(message = "Please fill out price")
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weekend_house_id")
    private WeekendHouse weekendHouse;



    public WeekendHouseReservation() {
    }

    public WeekendHouseReservation(Integer id, Date startDateTime, Date endDateTime, Integer peopleNumber, Set<AdditionalService> services, Float price, Customer customer, WeekendHouse weekendHouse) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.peopleNumber = peopleNumber;
        this.services = services;
        this.price = price;
        this.customer = customer;
        this.weekendHouse = weekendHouse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Set<AdditionalService> getServices() {
        return services;
    }

    public void setServices(Set<AdditionalService> services) {
        this.services = services;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WeekendHouse getWeekendHouse() {
        return weekendHouse;
    }

    public void setWeekendHouse(WeekendHouse weekendHouse) {
        this.weekendHouse = weekendHouse;
    }
}
