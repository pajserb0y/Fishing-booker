package com.springboot.app.model;

import com.springboot.app.model.dto.AdditionalServiceDTO;
import com.springboot.app.model.dto.FishingLessonDTO;
import com.springboot.app.model.dto.TermFishingLessonDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FishingLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private String description;

    private String imagePaths;

    private Integer maxNumberOfPeople;

    @OneToMany(mappedBy = "fishingLesson", fetch = FetchType.EAGER
            , cascade = CascadeType.ALL)
    private Set<TermFishingLesson> freeTerms = new HashSet<>();

    private String rules;

    private String fishingEquipment;

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

    @ManyToMany(mappedBy="subscribedFishingLessons",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Customer> subscribedCustomers = new HashSet<>();



    public FishingLesson() {

    }

    public FishingLesson(Integer id, String name, String address, String description, String imagePaths, Integer maxNumberOfPeople, Set<TermFishingLesson> freeTerms, String rules, String fishingEquipment, Float price, Set<AdditionalService> additionalServices, String cancelConditions, Instructor instructor, Set<FishingLessonReservation> fishingLessonReservations) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
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

    public FishingLesson(FishingLessonDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.description = dto.getDescription();
        this.imagePaths = dto.getImagePaths();
        this.fishingEquipment = dto.getFishingEquipment();
        Set<TermFishingLesson> terms = new HashSet<>();
        for (TermFishingLessonDTO termDto : dto.getFreeTerms())
            terms.add(new TermFishingLesson(termDto));
        this.freeTerms = terms;
        this.price = dto.getPrice();
        this.maxNumberOfPeople = dto.getMaxNumberOfPeople();
        this.rules = dto.getRules();
        this.cancelConditions = dto.getCancelConditions();

        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : dto.getAdditionalServices())
            services.add(new AdditionalService(service));
        this.additionalServices = services;

        this.instructor = new Instructor(dto.getInstructor());
    }

    public void edit(FishingLessonDTO dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.description = dto.getDescription();
        this.imagePaths = dto.getImagePaths();
        this.fishingEquipment = dto.getFishingEquipment();
        this.price = dto.getPrice();
        this.maxNumberOfPeople = dto.getMaxNumberOfPeople();
        this.rules = dto.getRules();
        this.cancelConditions = dto.getCancelConditions();

        Set<TermFishingLesson> terms = new HashSet<>();
        for (TermFishingLessonDTO termDto : dto.getFreeTerms())
            terms.add(new TermFishingLesson(termDto));
        this.freeTerms = terms;

        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : dto.getAdditionalServices())
            services.add(new AdditionalService(service));
        this.additionalServices = services;

        this.instructor = new Instructor(dto.getInstructor());
    }

    public String getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
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

    public Set<TermFishingLesson> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(Set<TermFishingLesson> freeTerms) {
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

    public Set<Customer> getSubscribedCustomers() {
        return subscribedCustomers;
    }

    public void setSubscribedCustomers(Set<Customer> subscribedCustomers) {
        this.subscribedCustomers = subscribedCustomers;
    }
}