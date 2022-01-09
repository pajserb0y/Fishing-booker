package com.springboot.app.model.dto;

import com.springboot.app.model.BoatOwner;

public class BoatOwnerDTO extends UserDTO {
    private String motive;
    public BoatOwnerDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, String motive) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone);
        this.motive = motive;
    }

    public BoatOwnerDTO(BoatOwner boatOwner) {
        super(boatOwner);
        this.motive = boatOwner.getMotive();
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}
