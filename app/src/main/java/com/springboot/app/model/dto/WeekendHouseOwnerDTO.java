package com.springboot.app.model.dto;

import com.springboot.app.model.User;
import com.springboot.app.model.WeekendHouseOwner;

public class WeekendHouseOwnerDTO extends UserDTO{
    public WeekendHouseOwnerDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone);
    }

    public WeekendHouseOwnerDTO(WeekendHouseOwner weekendHouseOwner) {
        super(weekendHouseOwner);
    }
}
