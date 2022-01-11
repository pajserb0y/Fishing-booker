package com.springboot.app.model.dto;

import com.springboot.app.model.AdditionalService;

public class AdditionalServiceDTO {
    private Integer id;
    private String name;
    private Float price;

    public AdditionalServiceDTO() {
    }

    public AdditionalServiceDTO(AdditionalService service) {
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
