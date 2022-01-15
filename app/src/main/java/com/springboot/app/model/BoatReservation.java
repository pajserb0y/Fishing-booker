package com.springboot.app.model;

import com.springboot.app.model.dto.AdditionalServiceDTO;
import com.springboot.app.model.dto.BoatReservationDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boat_reservation")
public class BoatReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date startDateTime;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date endDateTime;

    private Integer capacity;

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
    @JoinColumn(name = "boat_id")
    private Boat boat;

    private boolean isCancelled = false;



    public BoatReservation() {
    }

    public BoatReservation(Integer id, Date startDateTime, Date endDateTime, Integer capacity, @Nullable Date startSpecialOffer, @Nullable Date endSpecialOffer, Set<AdditionalService> services, Float price, Customer customer, Boat boat, boolean isCancelled) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.capacity = capacity;
        this.startSpecialOffer = startSpecialOffer;
        this.endSpecialOffer = endSpecialOffer;
        this.services = services;
        this.price = price;
        this.customer = customer;
        this.boat = boat;
        this.isCancelled = isCancelled;
    }

    public BoatReservation(BoatReservationDTO res) {
        this.id = res.getId();
        this.startDateTime = res.getStartDateTime();
        this.endDateTime = res.getEndDateTime();
        this.capacity = res.getCapacity();
        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : res.getServices())
            services.add(new AdditionalService(service));
        this.startSpecialOffer = res.getStartSpecialOffer();
        this.endSpecialOffer = res.getEndSpecialOffer();
        this.services = services;
        this.price = res.getPrice();
        this.customer =  new Customer(res.getCustomer());
        this.boat = new Boat(res.getBoat());
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Nullable
    public Date getStartSpecialOffer() {
        return startSpecialOffer;
    }

    public void setStartSpecialOffer(@Nullable Date startSpecialOffer) {
        this.startSpecialOffer = startSpecialOffer;
    }

    @Nullable
    public Date getEndSpecialOffer() {
        return endSpecialOffer;
    }

    public void setEndSpecialOffer(@Nullable Date endSpecialOffer) {
        this.endSpecialOffer = endSpecialOffer;
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

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
