package com.springboot.app.model;

import com.springboot.app.model.dto.AdditionalServiceDTO;
import com.springboot.app.model.dto.BoatDTO;
import com.springboot.app.model.dto.TermBoatDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please fill out name")
    private String name;

    @NotEmpty(message = "Please fill out type")
    private String type;

    @NotEmpty(message = "Please fill out adress")
    private String address;

    private Double length;

    private Double engineNumber;

    private Double horsePower;

    private Double maxSpeed;

    @NotEmpty(message = "Please fill out description")
    private String description;

    private String imagePath;

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TermBoat> freeTerms = new HashSet<>();

    private Float price;

    @NotEmpty(message = "Please fill out rules")
    private String rules;

    private Integer capacity;


    @ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "boat_services",
            joinColumns = @JoinColumn(name = "boat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<AdditionalService> additionalServices = new HashSet<>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BoatReservation> boatReservations = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;

    @ManyToMany(mappedBy="subscribedBoats",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Customer> subscribedCustomers = new HashSet<>();


    public Boat() {
    }

    public Boat(BoatDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.type = dto.getType();
        this.horsePower = dto.getHorsePower();
        this.engineNumber = dto.getEngineNumber();
        this.length = dto.getLength();
        this.maxSpeed = dto.getMaxSpeed();
        this.description = dto.getDescription();
        this.imagePath = "";
        this.address = dto.getAddress();
        Set<TermBoat> terms = new HashSet<>();
        for (TermBoatDTO termDto : dto.getFreeTerms())
            terms.add(new TermBoat(termDto));
        this.freeTerms = terms;
        this.price = dto.getPrice();
        this.capacity = dto.getCapacity();
        this.rules = dto.getRules();

        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : dto.getAdditionalServices())
            services.add(new AdditionalService(service));
        this.additionalServices = services;

        this.boatOwner = new BoatOwner(dto.getBoatOwner());
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


    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Set<TermBoat> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(Set<TermBoat> freeTerms) {
        this.freeTerms = freeTerms;
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

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
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

    public Set<BoatReservation> getBoatReservations() {
        return boatReservations;
    }

    public void setBoatReservations(Set<BoatReservation> boatReservations) {
        this.boatReservations = boatReservations;
    }

    public Set<Customer> getSubscribedCustomers() {
        return subscribedCustomers;
    }

    public void setSubscribedCustomers(Set<Customer> subscribedCustomers) {
        this.subscribedCustomers = subscribedCustomers;
    }
}
