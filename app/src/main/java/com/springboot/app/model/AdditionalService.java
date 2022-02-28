package com.springboot.app.model;

import com.springboot.app.model.dto.AdditionalServiceDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float price;

    public AdditionalService() {
    }

    public AdditionalService(AdditionalServiceDTO service) {
        this.id = service.getId();
        this.name = service.getName();
        this.price = service.getPrice();
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
