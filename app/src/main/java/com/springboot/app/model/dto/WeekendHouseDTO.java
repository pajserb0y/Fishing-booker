package com.springboot.app.model.dto;

import com.springboot.app.model.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class WeekendHouseDTO {

    private Integer id;

    @NotEmpty(message = "Please fill out name")
    private String name;

    @NotEmpty(message = "Please fill out adress")
    private String address;

    @NotEmpty(message = "Please fill out description")
    private String description;

    //@NotEmpty(message = "Please fill out first name")
    private Set<String> imagePath = new HashSet<>();

    //@NotEmpty(message = "Please fill out number of beds in weekend house")
    private Integer bedNumber;

    private Set<TermDto> freeTerms = new HashSet<>();

    @NotEmpty(message = "Please fill out rules")
    private String rules;

    //@NotEmpty(message = "Please fill out price lists")
    private Float price;

    //@NotEmpty(message = "Please fill out services")
    private Set<AdditionalServiceDTO> additionalServices = new HashSet<>();

    private WeekendHouseOwnerDTO weekendHouseOwner;

    private Set<WeekendHouseReservationDTO> weekendHouseReservations = new HashSet<>();


    public WeekendHouseDTO() {
    }

    public WeekendHouseDTO(WeekendHouse house) {
        this.id = house.getId();
        Set<AdditionalServiceDTO> services = new HashSet<>();
        for (AdditionalService service : house.getAdditionalServices())
           services.add(new AdditionalServiceDTO(service));
        this.additionalServices = services;
        this.address = house.getAddress();
        this.bedNumber = house.getBedNumber();
        this.description = house.getDescription();

//        Set<TermDto> terms = new HashSet<>();
//        for (Term term : house.getFreeTerms())
//            terms.add(new TermDto(term));
//        this.freeTerms = terms;

        this.name = house.getName();
        this.imagePath = new HashSet<>();
        this.price = house.getPrice();
        this.rules = house.getRules();
        this.weekendHouseOwner = new WeekendHouseOwnerDTO(house.getWeekendHouseOwner());

//        Set<WeekendHouseReservationDTO> reservations = new HashSet<>();
//        for (WeekendHouseReservation res : house.getWeekendHouseReservations())
//            reservations.add(new WeekendHouseReservationDTO(res));
//        this.weekendHouseReservations = reservations;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(Set<String> imagePath) {
        this.imagePath = imagePath;
    }


    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Set<TermDto> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(Set<TermDto> freeTerms) {
        this.freeTerms = freeTerms;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalServiceDTO> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public WeekendHouseOwnerDTO getWeekendHouseOwner() {
        return weekendHouseOwner;
    }

    public void setWeekendHouseOwner(WeekendHouseOwnerDTO weekendHouseOwner) {
        this.weekendHouseOwner = weekendHouseOwner;
    }

    public Set<WeekendHouseReservationDTO> getWeekendHouseReservations() {
        return weekendHouseReservations;
    }

    public void setWeekendHouseReservations(Set<WeekendHouseReservationDTO> weekendHouseReservations) {
        this.weekendHouseReservations = weekendHouseReservations;
    }
}
