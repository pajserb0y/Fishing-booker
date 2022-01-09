package com.springboot.app.service;

import com.springboot.app.model.Role;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;
import com.springboot.app.repository.WeekendHouseOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WeekendHouseServiceImpl implements WeekendHouseOwnerService{
    private final WeekendHouseOwnerRepository weekendHouseOwnerRepository;
    private final RoleService roleService;

    public WeekendHouseServiceImpl(WeekendHouseOwnerRepository weekendHouseOwnerRepository, RoleService roleService) {
        this.weekendHouseOwnerRepository = weekendHouseOwnerRepository;
        this.roleService = roleService;
    }

    @Override
    public WeekendHouseOwner saveWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner) {
        List<Role> roles = roleService.findByName("ROLE_WEEKEND_HOUSE_OWNER");
        weekendHouseOwner.setRole(roles.get(0));
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
}
