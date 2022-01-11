package com.springboot.app.service;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;

import java.util.HashSet;
import java.util.List;

public interface WeekendHouseOwnerService {
    WeekendHouseOwner saveWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner);
    WeekendHouseOwner findByUsername(String username);
    WeekendHouseOwner changeWeekendHouseOwner(WeekendHouseOwnerDTO weekendHouseOwnerDTO);

    void saveWeekendHouse(WeekendHouse weekendHouse);

    List<WeekendHouse> findAllWeekendHouses();
}
