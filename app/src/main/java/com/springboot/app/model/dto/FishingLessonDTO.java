package com.springboot.app.model.dto;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class FishingLessonDTO {

    private Integer id;

    @NotEmpty(message = "Please fill out title")
    private String title;

    @NotEmpty(message = "Please fill out address")
    private String address;

    @NotEmpty(message = "Please fill out description")
    private String description;

    @NotEmpty(message = "Please fill out instructors biography")
    private String instructorBiography;

    @NotEmpty(message = "Please fill out max number of people")
    private Integer maxNumberOfPeople;

    private Set<TermDto> freeTerms = new HashSet<>();

    @NotEmpty(message = "Please fill out rules")
    private String rules;

    private Set<String> fishingEquipment = new HashSet<>();

    @NotEmpty(message = "Please fill out price")
    private Float price;

    private Set<AdditionalServiceDTO> additionalServices = new HashSet<>();

    @NotEmpty(message = "Please fill out cancel conditions")
    private String cancelConditions;

    private InstructorDTO instructor;

    private Set<FishingLessonReservationDTO> fishingLessonReservations;

    public FishingLessonDTO() {

    }

    public FishingLessonDTO(Integer id, String title, String address, String description, String instructorBiography, Integer maxNumberOfPeople, Set<TermDto> freeTerms, String rules, Set<String> fishingEquipment, Float price, Set<AdditionalServiceDTO> additionalServices, String cancelConditions, InstructorDTO instructor, Set<FishingLessonReservationDTO> fishingLessonReservations) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.freeTerms = freeTerms;
        this.rules = rules;
        this.fishingEquipment = fishingEquipment;
        this.price = price;
        this.additionalServices = additionalServices;
        this.cancelConditions = cancelConditions;
        this.instructor = instructor;
        this.fishingLessonReservations = fishingLessonReservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getInstructorBiography() {
        return instructorBiography;
    }

    public void setInstructorBiography(String instructorBiography) {
        this.instructorBiography = instructorBiography;
    }

    public Integer getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(Integer maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
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

    public Set<String> getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(Set<String> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
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

    public String getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(String cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public InstructorDTO getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorDTO instructor) {
        this.instructor = instructor;
    }

    public Set<FishingLessonReservationDTO> getFishingLessonReservations() {
        return fishingLessonReservations;
    }

    public void setFishingLessonReservations(Set<FishingLessonReservationDTO> fishingLessonReservations) {
        this.fishingLessonReservations = fishingLessonReservations;
    }
}
