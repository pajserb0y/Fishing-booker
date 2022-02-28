package com.springboot.app.model.dto;

import com.springboot.app.model.AdditionalService;
import com.springboot.app.model.Customer;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class WeekendHouseReservationDTO {

    private Integer id;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date startDateTime;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date endDateTime;

    private Integer peopleNumber;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date startSpecialOffer = new Date();

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date endSpecialOffer;

    private Set<AdditionalServiceDTO> services = new HashSet<>();

    private Float price;

    private CustomerDTO customer;

    private WeekendHouseDTO weekendHouse;

    private boolean isCancelled;


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
        this.endSpecialOffer = res.getEndSpecialOffer();
        if(res.getCustomer() != null)
            this.customer = new CustomerDTO(res.getCustomer());
        else
            this.customer = null;
        this.weekendHouse = new WeekendHouseDTO(res.getWeekendHouse());
        this.isCancelled = res.isCancelled();
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

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
