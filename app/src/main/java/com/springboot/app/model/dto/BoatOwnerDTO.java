package com.springboot.app.model.dto;

import com.springboot.app.model.BoatOwner;

public class BoatOwnerDTO extends UserDTO {
    public BoatOwnerDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone);
    }

    public BoatOwnerDTO(BoatOwner boatOwner) {
        super(boatOwner);
    }
}
