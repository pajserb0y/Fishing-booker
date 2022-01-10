package com.springboot.app.model.dto;

import com.springboot.app.model.WeekendHouseOwner;

import javax.validation.constraints.NotEmpty;

public class WeekendHouseOwnerDTO extends UserDTO{
    @NotEmpty(message = "Please fill out phone")
    private String motive;
    public WeekendHouseOwnerDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, String motive) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone);
        this.motive = motive;
    }

    public WeekendHouseOwnerDTO() {
        super();
    }

    public WeekendHouseOwnerDTO(WeekendHouseOwner weekendHouseOwner) {
        super(weekendHouseOwner);
        this.motive = weekendHouseOwner.getMotive();
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}
