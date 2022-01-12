package com.springboot.app.service;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.model.dto.WeekendHouseDTO;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;

import java.util.Collection;
import java.util.Optional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface WeekendHouseOwnerService {
    WeekendHouseOwner saveWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner);
    WeekendHouseOwner findByUsername(String username);
    WeekendHouseOwner changeWeekendHouseOwner(WeekendHouseOwnerDTO weekendHouseOwnerDTO);

    void saveWeekendHouse(WeekendHouse weekendHouse);

    Collection<String> findAllUsernames();

    Optional<WeekendHouseOwner> findById(int id);

    void setWantedToDelete(int id);
    List<WeekendHouse> findAllWeekendHouses();

    List<WeekendHouse> findAvailableHousesForDateRange(DateTimeRangeDTO dateRange);
}
