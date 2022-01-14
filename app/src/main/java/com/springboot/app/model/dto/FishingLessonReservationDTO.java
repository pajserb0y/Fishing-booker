package com.springboot.app.model.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FishingLessonReservationDTO {

    private Integer id;

    private String venueAddress;

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

    private Set<AdditionalServiceDTO> additionalServices = new HashSet<>();

    private Float price;

    private CustomerDTO customer;

    private FishingLessonDTO fishingLesson;

    public FishingLessonReservationDTO() {

    }

    public FishingLessonReservationDTO(Integer id, String venueAddress, Date startDateTime, Date endDateTime, Integer maxPeopleNumber, @Nullable Date startSpecialOffer, @Nullable Date endSpecialOffer, Set<AdditionalServiceDTO> additionalServices, Float price, CustomerDTO customer, FishingLessonDTO fishingLesson) {
        this.id = id;
        this.venueAddress = venueAddress;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.maxPeopleNumber = maxPeopleNumber;
        this.startSpecialOffer = startSpecialOffer;
        this.endSpecialOffer = endSpecialOffer;
        this.additionalServices = additionalServices;
        this.price = price;
        this.customer = customer;
        this.fishingLesson = fishingLesson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
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

    public void setMaxPeopleNumber(Integer maxPeopleNumber) {
        this.maxPeopleNumber = maxPeopleNumber;
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

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalServiceDTO> additionalServices) {
        this.additionalServices = additionalServices;
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

    public FishingLessonDTO getFishingLesson() {
        return fishingLesson;
    }

    public void setFishingLesson(FishingLessonDTO fishingLesson) {
        this.fishingLesson = fishingLesson;
    }
}
