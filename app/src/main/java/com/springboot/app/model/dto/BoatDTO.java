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

    @NotEmpty(message = "Please fill out description")
    private String description;

    private Set<String> imagePath = new HashSet<>();

    @NotEmpty(message = "Please fill out type")
    private String type;

    @NotEmpty(message = "Please fill out address")
    private String address;

    private Double length;

    private Double engineNumber;

    private Double horsePower;

    private Double maxSpeed;

    private Set<TermBoatDTO> freeTerms = new HashSet<>();

    private Float price;

    @NotEmpty(message = "Please fill out rules")
    private String rules;

    private Integer capacity;

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
        this.type = boat.getType();
        this.horsePower = boat.getHorsePower();
        this.maxSpeed = boat.getMaxSpeed();
        this.engineNumber = boat.getEngineNumber();
        this.length = boat.getLength();
        this.description = boat.getDescription();
        this.rules = boat.getRules();
        this.capacity = boat.getCapacity();
        this.address = boat.getAddress();
        this.name = boat.getName();
        this.imagePath = new HashSet<>();
        this.price = boat.getPrice();
        this.boatOwner = new BoatOwnerDTO(boat.getBoatOwner());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getEngineNumber() {
        return engineNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEngineNumber(Double engineNumber) {
        this.engineNumber = engineNumber;
    }

    public Double getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Double horsePower) {
        this.horsePower = horsePower;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
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
