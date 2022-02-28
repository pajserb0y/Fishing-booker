package com.springboot.app.model;

import com.springboot.app.model.dto.AdditionalServiceDTO;
import com.springboot.app.model.dto.WeekendHouseReservationDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "weekend_house_reservation")
public class WeekendHouseReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date startDateTime;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date endDateTime;

    private Integer peopleNumber;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Nullable
    private Date startSpecialOffer = new Date();

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Nullable
    private Date endSpecialOffer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "weekend_house_reservation_services",
            joinColumns = @JoinColumn(name = "weekend_house_reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<AdditionalService> services = new HashSet<>();

    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weekend_house_id")
    private WeekendHouse weekendHouse;

    private boolean isCancelled = false;





    public WeekendHouseReservation() {
    }

    public WeekendHouseReservation(Integer id, Date startDateTime, Date endDateTime, Integer peopleNumber, Set<AdditionalService> services, Float price, Customer customer, WeekendHouse weekendHouse, boolean isCancelled) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.peopleNumber = peopleNumber;
        this.services = services;
        this.price = price;
        this.customer = customer;
        this.weekendHouse = weekendHouse;
        this.isCancelled = isCancelled;
    }

    public WeekendHouseReservation(WeekendHouseReservationDTO res) {
        this.id = res.getId();
        this.startDateTime = res.getStartDateTime();
        this.endDateTime = res.getEndDateTime();
        this.peopleNumber = res.getPeopleNumber();
        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : res.getServices())
            services.add(new AdditionalService(service));
        this.startSpecialOffer = res.getStartSpecialOffer();
        this.endSpecialOffer = res.getEndSpecialOffer();
        this.services = services;
        this.price = res.getPrice();
        if(res.getCustomer() != null)
            this.customer =  new Customer(res.getCustomer());
        else
            this.customer = null;
        this.weekendHouse = new WeekendHouse(res.getWeekendHouse());
        this.isCancelled = res.isCancelled();
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

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

}
