package com.springboot.app.model.dto;

import com.springboot.app.model.AdditionalService;
import com.springboot.app.model.Customer;
import com.springboot.app.model.FishingLesson;
import com.springboot.app.model.FishingLessonReservation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FishingLessonReservationDTO {

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

    private Set<AdditionalServiceDTO> additionalServices = new HashSet<>();

    private Float price;

    private CustomerDTO customer;

    private FishingLessonDTO fishingLesson;

    private boolean isCancelled;

    public FishingLessonReservationDTO() {

    }

    public FishingLessonReservationDTO(FishingLessonReservation res) {
        this.id = res.getId();
        this.startDateTime = res.getStartDateTime();
        this.endDateTime = res.getEndDateTime();
        this.maxPeopleNumber = res.getMaxPeopleNumber();
        Set<AdditionalServiceDTO> services = new HashSet<>();
        for (AdditionalService service : res.getAdditionalServices())
            services.add(new AdditionalServiceDTO(service));
        this.additionalServices = services;

        this.startSpecialOffer = res.getStartSpecialOffer();
        this.endSpecialOffer = res.getEndSpecialOffer();
        this.price = res.getPrice();
        if(res.getCustomer() != null)
            this.customer = new CustomerDTO(res.getCustomer());
        else
            this.customer = null;
        this.fishingLesson = new FishingLessonDTO(res.getFishingLesson());
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

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
