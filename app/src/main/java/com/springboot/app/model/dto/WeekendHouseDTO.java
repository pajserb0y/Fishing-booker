package com.springboot.app.model.dto;

import com.springboot.app.model.AdditionalService;
import com.springboot.app.model.Term;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class WeekendHouseDTO {

    private Integer id;

    @NotEmpty(message = "Please fill out name")
    private String name;

    @NotEmpty(message = "Please fill out adress")
    private String adress;

    @NotEmpty(message = "Please fill out description")
    private String description;

    //@NotEmpty(message = "Please fill out first name")
    private String imagePath;

    @NotEmpty(message = "Please fill out number of beds in weekend house")
    private Integer bedNumber;

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
    private Set<AdditionalService> additionalServices;


    public WeekendHouseDTO() {
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
}
