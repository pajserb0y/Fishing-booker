package com.springboot.app.model;

import com.springboot.app.model.dto.AdditionalServiceDTO;
import com.springboot.app.model.dto.WeekendHouseDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="weekend_house")
public class WeekendHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please fill out name")
    private String name;

    @NotEmpty(message = "Please fill out adress")
    private String address;

    @NotEmpty(message = "Please fill out description")
    private String description;

    //@NotEmpty(message = "Please fill out first name")
    private String imagePath;


    @NotEmpty(message = "Please fill out number of beds in weekend house")
    private Integer bedNumber;

    //@NotEmpty(message = "Please fill out first name")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "freeTerms",
            joinColumns = @JoinColumn(name = "weekend_house_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "term_id", referencedColumnName = "id"))
    private Set<Term> freeTerms = new HashSet<>();

    @NotEmpty(message = "Please fill out rules")
    private String rules;

    @NotEmpty(message = "Please fill out price lists")
    private Float price;

    //@NotEmpty(message = "Please fill out services")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "weekend_house_services",
            joinColumns = @JoinColumn(name = "weekend_house_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<AdditionalService> additionalServices = new HashSet<>();

    @OneToMany(mappedBy = "weekendHouse", fetch = FetchType.EAGER
            , cascade = CascadeType.ALL)
    private Set<WeekendHouseReservation> weekendHouseReservations = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weekend_house_owner_id")
    private WeekendHouseOwner weekendHouseOwner;


    public WeekendHouse() {
    }

    public WeekendHouse(WeekendHouseDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.description = dto.getDescription();
        this.imagePath = dto.getImagePath();
        this.bedNumber = dto.getBedNumber();
        this.freeTerms = dto.getFreeTerms();
        this.rules = dto.getRules();
        this.price = dto.getPrice();

//        Set<AdditionalService> services = new HashSet<>();
//        for (AdditionalServiceDTO service : dto.getAdditionalServices())
//            services.add(new AdditionalService(service));
//        this.additionalServices = services;
    }

    public WeekendHouse(Integer id, String name, String address, String description, String imagePath, Integer bedNumber, Set<Term> freeTerms, String rules, Float price, Set<AdditionalService> additionalServices, Set<WeekendHouseReservation> weekendHouseReservations) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.imagePath = imagePath;
        this.bedNumber = bedNumber;
        this.freeTerms = freeTerms;
        this.rules = rules;
        this.price = price;
        this.additionalServices = additionalServices;
        this.weekendHouseReservations = weekendHouseReservations;
    }

    public WeekendHouseOwner getWeekendHouseOwner() {
        return weekendHouseOwner;
    }

    public void setWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner) {
        this.weekendHouseOwner = weekendHouseOwner;
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


    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
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

    public Set<WeekendHouseReservation> getWeekendHouseReservations() {
        return weekendHouseReservations;
    }

    public void setWeekendHouseReservations(Set<WeekendHouseReservation> weekendHouseReservations) {
        this.weekendHouseReservations = weekendHouseReservations;
    }
}
