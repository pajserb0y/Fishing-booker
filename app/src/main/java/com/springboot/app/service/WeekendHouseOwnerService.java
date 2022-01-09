package com.springboot.app.service;

import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;

public interface WeekendHouseOwnerService {
    WeekendHouseOwner saveWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner);
    WeekendHouseOwner findByUsername(String username);
    WeekendHouseOwner changeWeekendHouseOwner(WeekendHouseOwnerDTO weekendHouseOwnerDTO);
}
