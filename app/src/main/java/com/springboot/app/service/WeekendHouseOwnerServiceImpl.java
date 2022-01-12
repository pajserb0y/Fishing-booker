package com.springboot.app.service;

import com.springboot.app.model.Role;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.WeekendHouseReservation;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.model.dto.WeekendHouseDTO;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;
import com.springboot.app.repository.WeekendHouseOwnerRepository;
import com.springboot.app.repository.WeekendHouseRepository;
import com.springboot.app.repository.WeekendHouseReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WeekendHouseOwnerServiceImpl implements WeekendHouseOwnerService{
    private final WeekendHouseOwnerRepository weekendHouseOwnerRepository;
    private final WeekendHouseRepository weekendHouseRepository;
    private final WeekendHouseReservationRepository weekendHouseReservationRepository;
    private final RoleService roleService;

    public WeekendHouseOwnerServiceImpl(WeekendHouseOwnerRepository weekendHouseOwnerRepository, WeekendHouseRepository weekendHouseRepository,
                                        RoleService roleService, WeekendHouseReservationRepository weekendHouseReservationRepository) {
        this.weekendHouseOwnerRepository = weekendHouseOwnerRepository;
        this.roleService = roleService;
        this.weekendHouseRepository = weekendHouseRepository;
        this.weekendHouseReservationRepository = weekendHouseReservationRepository;
    }

    @Override
    public WeekendHouseOwner saveWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner) {
        List<Role> roles = roleService.findByName("ROLE_WEEKEND_HOUSE_OWNER");
        weekendHouseOwner.setRole(roles.get(0));
        weekendHouseOwner.setActivated(true);       //admin treba da odobri aktivaciju naloga koji nisu customer
        weekendHouseOwnerRepository.save(weekendHouseOwner);
        return null;
    }

    @Override
    public WeekendHouseOwner findByUsername(String username) {
        return weekendHouseOwnerRepository.findByUsername(username);
    }

    @Override
    public WeekendHouseOwner changeWeekendHouseOwner(WeekendHouseOwnerDTO weekendHouseOwnerDTO) {
        WeekendHouseOwner weekendHouseOwner = findByUsername(weekendHouseOwnerDTO.getUsername());

        weekendHouseOwner.setPhone(weekendHouseOwnerDTO.getPhone());
        weekendHouseOwner.setCountry(weekendHouseOwnerDTO.getCountry());
        weekendHouseOwner.setCity(weekendHouseOwnerDTO.getCity());
        weekendHouseOwner.setAddress(weekendHouseOwnerDTO.getAddress());
        weekendHouseOwner.setLastName(weekendHouseOwnerDTO.getLastName());
        weekendHouseOwner.setFirstName(weekendHouseOwnerDTO.getFirstName());
        weekendHouseOwner.setMotive(weekendHouseOwnerDTO.getMotive());

        saveWeekendHouseOwner(weekendHouseOwner);

        return null;
    }

    @Override
    public void saveWeekendHouse(WeekendHouse weekendHouse) {
        weekendHouseRepository.save(weekendHouse);
    }

    @Override
    public List<WeekendHouse> findAllWeekendHouses() {
        return weekendHouseRepository.findAll();
    }

    @Override
    public List<WeekendHouse> findAvailableHousesForDateRange(DateTimeRangeDTO dateRange) {
        Date start = DateTimeRangeDTO.ConvertFromStringToDate(dateRange.getStart());
        Date end = DateTimeRangeDTO.ConvertFromStringToDate(dateRange.getEnd());

        List<Integer> weekendHouseIds = weekendHouseReservationRepository.findAllForDateRange(start, end);
        List<WeekendHouse> available = weekendHouseRepository.findAvailable(weekendHouseIds);
        return available;
    }
}
