package com.springboot.app.model.dto;

import com.springboot.app.model.AdditionalService;
import com.springboot.app.model.FishingLesson;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class FishingLessonDTO {

    private Integer id;

    @NotEmpty(message = "Please fill out title")
    private String name;

    @NotEmpty(message = "Please fill out address")
    private String address;

    @NotEmpty(message = "Please fill out description")
    private String description;

    private String imagePaths;

    @NotEmpty(message = "Please fill out max number of people")
    private Integer maxNumberOfPeople;

    private Set<TermFishingLessonDTO> freeTerms = new HashSet<>();

    @NotEmpty(message = "Please fill out rules")
    private String rules;

    private String fishingEquipment;

    @NotEmpty(message = "Please fill out price")
    private Float price;

    private Set<AdditionalServiceDTO> additionalServices = new HashSet<>();

    @NotEmpty(message = "Please fill out cancel conditions")
    private String cancelConditions;

    private InstructorDTO instructor;

    private Set<FishingLessonReservationDTO> fishingLessonReservations;

    private double avgGrade;

    public FishingLessonDTO() {

    }

    public FishingLessonDTO(Integer id, String name, String address, String description, Integer maxNumberOfPeople, Set<TermFishingLessonDTO> freeTerms, String rules, String fishingEquipment, Float price, Set<AdditionalServiceDTO> additionalServices, String cancelConditions, InstructorDTO instructor, Set<FishingLessonReservationDTO> fishingLessonReservations) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
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

    public FishingLessonDTO(FishingLesson fishingLesson) {
        this.id = fishingLesson.getId();
        Set<AdditionalServiceDTO> services = new HashSet<>();
        for (AdditionalService service : fishingLesson.getAdditionalServices())
            services.add(new AdditionalServiceDTO(service));
        this.additionalServices = services;
        this.address = fishingLesson.getAddress();
        this.description = fishingLesson.getDescription();
        this.rules = fishingLesson.getRules();
        this.maxNumberOfPeople = fishingLesson.getMaxNumberOfPeople();
        this.cancelConditions = fishingLesson.getCancelConditions();
        this.fishingEquipment = fishingLesson.getFishingEquipment();

//        Set<TermDto> terms = new HashSet<>();
//        for (Term term : house.getFreeTerms())
//            terms.add(new TermDto(term));
//        this.freeTerms = terms;

        this.name = fishingLesson.getName();        //otisao sam sa bratom sad ovo da vidim, nastavi da menjas kontroler za instruktora, analogno weekend house kontroleru
        this.imagePaths = fishingLesson.getImagePaths();
        this.price = fishingLesson.getPrice();
        this.instructor = new InstructorDTO(fishingLesson.getInstructor());
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


    public Integer getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(Integer maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public Set<TermFishingLessonDTO> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(Set<TermFishingLessonDTO> freeTerms) {
        this.freeTerms = freeTerms;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
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

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public String getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }
}
