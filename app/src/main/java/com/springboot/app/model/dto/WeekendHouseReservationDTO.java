package com.springboot.app.model.dto;

import com.springboot.app.model.AdditionalService;
import com.springboot.app.model.Customer;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class WeekendHouseReservationDTO {

    private Integer id;
    @NotEmpty(message = "Please fill out starting date")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date startDateTime;

    @NotEmpty(message = "Please fill out duration of stay")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date endDateTime;

    @NotEmpty(message = "Please fill out number of people")
    private Integer peopleNumber;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date startSpecialOffer = new Date();

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date endSpecialOffer;

    private Set<AdditionalServiceDTO> services = new HashSet<>();

    @NotEmpty(message = "Please fill out price")
    private Float price;

    private CustomerDTO customer;

    private WeekendHouseDTO weekendHouse;


    public WeekendHouseReservationDTO() {
    }

    public WeekendHouseReservationDTO(WeekendHouseReservation res) {
        this.id = res.getId();
        this.startDateTime = res.getStartDateTime();
        this.endDateTime = res.getEndDateTime();
        this.peopleNumber = res.getPeopleNumber();
        this.price = res.getPrice();

        Set<AdditionalServiceDTO> services = new HashSet<>();
        for (AdditionalService service : res.getServices())
            services.add(new AdditionalServiceDTO(service));
        this.services = services;

        this.startSpecialOffer = res.getStartSpecialOffer();
        this.endSpecialOffer =res.getEndSpecialOffer();
        this.customer = new CustomerDTO(res.getCustomer());
        this.weekendHouse = new WeekendHouseDTO(res.getWeekendHouse());
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

    public Date getStartSpecialOffer() {
        return startSpecialOffer;
    }

    public void setStartSpecialOffer(Date startSpecialOffer) {
        this.startSpecialOffer = startSpecialOffer;
    }

    public Date getEndSpecialOffer() {
        return endSpecialOffer;
    }

    public void setEndSpecialOffer(Date endSpecialOffer) {
        this.endSpecialOffer = endSpecialOffer;
    }

    public Set<AdditionalServiceDTO> getServices() {
        return services;
    }

    public void setServices(Set<AdditionalServiceDTO> services) {
        this.services = services;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public WeekendHouseDTO getWeekendHouse() {
        return weekendHouse;
    }

    public void setWeekendHouse(WeekendHouseDTO weekendHouse) {
        this.weekendHouse = weekendHouse;
    }
}
