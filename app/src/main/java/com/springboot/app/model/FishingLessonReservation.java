package com.springboot.app.model;

import com.springboot.app.model.dto.AdditionalServiceDTO;
import com.springboot.app.model.dto.FishingLessonReservationDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fishing_lesson_reservation")
public class FishingLessonReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date startDateTime;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date endDateTime;

    private Integer maxPeopleNumber;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Nullable
    private Date startSpecialOffer = new Date();

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Nullable
    private Date endSpecialOffer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fishing_lesson_reservation_services",
            joinColumns = @JoinColumn(name = "fishing_lesson_reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<AdditionalService> additionalServices = new HashSet<>();

    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fishing_lesson_id")
    private FishingLesson fishingLesson;

    private boolean isCancelled = false;



    public FishingLessonReservation() {

    }

    public FishingLessonReservation(Integer id, Date startDateTime, Date endDateTime, Integer maxPeopleNumber, @Nullable Date startSpecialOffer, @Nullable Date endSpecialOffer, Set<AdditionalService> additionalServices, Float price, Customer customer, FishingLesson fishingLesson, boolean isCancelled) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.maxPeopleNumber = maxPeopleNumber;
        this.startSpecialOffer = startSpecialOffer;
        this.endSpecialOffer = endSpecialOffer;
        this.additionalServices = additionalServices;
        this.price = price;
        this.customer = customer;
        this.fishingLesson = fishingLesson;
        this.isCancelled = isCancelled;
    }

    public FishingLessonReservation(FishingLessonReservationDTO res) {
        this.id = res.getId();
        this.startDateTime = res.getStartDateTime();
        this.endDateTime = res.getEndDateTime();
        this.maxPeopleNumber = res.getMaxPeopleNumber();
        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : res.getAdditionalServices())
            services.add(new AdditionalService(service));
        this.additionalServices = services;

        this.startSpecialOffer = res.getStartSpecialOffer();
        this.endSpecialOffer = res.getEndSpecialOffer();
        this.price = res.getPrice();
        if(res.getCustomer() != null)
            this.customer =  new Customer(res.getCustomer());
        else
            this.customer = null;
        this.fishingLesson = new FishingLesson(res.getFishingLesson());
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

    public Integer getMaxPeopleNumber() {
        return maxPeopleNumber;
    }

    public void setMaxPeopleNumber(Integer maxpeopleNumber) {
        this.maxPeopleNumber = maxpeopleNumber;
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

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
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

    public FishingLesson getFishingLesson() {
        return fishingLesson;
    }

    public void setFishingLesson(FishingLesson fishingLesson) {
        this.fishingLesson = fishingLesson;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
