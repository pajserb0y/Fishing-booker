package com.springboot.app.model.dto;

import com.springboot.app.model.AdditionalService;
import com.springboot.app.model.Boat;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class BoatDTO {
    private Integer id;

    @NotEmpty(message = "Please fill out name")
    private String name;

    @NotEmpty(message = "Please fill out adress")
    private String address;

    @NotEmpty(message = "Please fill out description")
    private String description;

    //@NotEmpty(message = "Please fill out first name")
    private String imagePath;

    private Set<TermBoatDTO> freeTerms = new HashSet<>();

    @NotEmpty(message = "Please fill out price lists")
    private Float price;

    @NotEmpty(message = "Please fill out rules")
    private String rules;

    @NotEmpty(message = "Please fill out number of beds in weekend house")
    private Integer capacity;

    //@NotEmpty(message = "Please fill out services")
    private Set<AdditionalServiceDTO> additionalServices = new HashSet<>();

    private BoatOwnerDTO boatOwner;

    private Set<BoatReservationDTO> boatReservations = new HashSet<>();

    private Integer avgGrade;



    public BoatDTO() {
    }

    public BoatDTO(Boat boat) {
        this.id = boat.getId();
        Set<AdditionalServiceDTO> services = new HashSet<>();
        for (AdditionalService service : boat.getAdditionalServices())
            services.add(new AdditionalServiceDTO(service));
        this.additionalServices = services;
        this.address = boat.getAddress();
        this.description = boat.getDescription();
        this.rules = boat.getRules();
        this.capacity = boat.getCapacity();

//        Set<TermDto> terms = new HashSet<>();
//        for (Term term : house.getFreeTerms())
//            terms.add(new TermDto(term));
//        this.freeTerms = terms;

        this.name = boat.getName();
        this.imagePath = boat.getImagePath();
        this.price = boat.getPrice();
        this.boatOwner = new BoatOwnerDTO(boat.getBoatOwner());

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Set<TermBoatDTO> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(Set<TermBoatDTO> freeTerms) {
        this.freeTerms = freeTerms;
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

    public BoatOwnerDTO getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwnerDTO boatOwner) {
        this.boatOwner = boatOwner;
    }

    public Integer getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Integer avgGrade) {
        this.avgGrade = avgGrade;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<BoatReservationDTO> getBoatReservations() {
        return boatReservations;
    }

    public void setBoatReservations(Set<BoatReservationDTO> boatReservations) {
        this.boatReservations = boatReservations;
    }
}
