package com.springboot.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FishingLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String address;

    private String description;

    private String instructorBiography;

    private Set<String> imagePaths = new HashSet<>();

    private Integer maxNumberOfPeople;

    @OneToMany(mappedBy = "fishingLesson", fetch = FetchType.EAGER
            , cascade = CascadeType.ALL)
    private Set<Term> freeTerms = new HashSet<>();

    private String rules;

    private Set<String> fishingEquipment = new HashSet<>();

    private Float price;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "fishing_lesson_services",
            joinColumns = @JoinColumn(name = "fishing_lesson_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<AdditionalService> additionalServices = new HashSet<>();

    private String cancelConditions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fishing_lesson_instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "fishingLesson", fetch = FetchType.EAGER
            , cascade = CascadeType.ALL)
    private Set<FishingLessonReservation> fishingLessonReservations = new HashSet<>();

    public FishingLesson() {

    }

    public FishingLesson(Integer id, String title, String address, String description, String instructorBiography, Set<String> imagePaths, Integer maxNumberOfPeople, Set<Term> freeTerms, String rules, Set<String> fishingEquipment, Float price, Set<AdditionalService> additionalServices, String cancelConditions, Instructor instructor, Set<FishingLessonReservation> fishingLessonReservations) {

        this.id = id;
        this.title = title;
        this.address = address;
        this.description = description;
        this.instructorBiography = instructorBiography;
        this.imagePaths = imagePaths;
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

    public Set<Term> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(Set<Term> freeTerms) {
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

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(String cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<FishingLessonReservation> getFishingLessonReservations() {
        return fishingLessonReservations;
    }

    public void setFishingLessonReservations(Set<FishingLessonReservation> fishingLessonReservations) {
        this.fishingLessonReservations = fishingLessonReservations;
    }
}