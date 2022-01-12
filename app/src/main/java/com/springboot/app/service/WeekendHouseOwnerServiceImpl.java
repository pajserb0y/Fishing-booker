package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.Role;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;
import com.springboot.app.repository.WeekendHouseOwnerRepository;
import com.springboot.app.repository.WeekendHouseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class WeekendHouseOwnerServiceImpl implements WeekendHouseOwnerService{
    private final WeekendHouseOwnerRepository weekendHouseOwnerRepository;
    private final WeekendHouseRepository weekendHouseRepository;
    private final RoleService roleService;

    public WeekendHouseOwnerServiceImpl(WeekendHouseOwnerRepository weekendHouseOwnerRepository, WeekendHouseRepository weekendHouseRepository,
                                        RoleService roleService) {
        this.weekendHouseOwnerRepository = weekendHouseOwnerRepository;
        this.roleService = roleService;
        this.weekendHouseRepository = weekendHouseRepository;
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
    public Collection<String> findAllUsernames() {
        return weekendHouseOwnerRepository.findAllUsernames();
    }

    @Override
    public Optional<WeekendHouseOwner> findById(int id) {
        return weekendHouseOwnerRepository.findById(id);
    }

    @Override
    public void setWantedToDelete(int id) {
        Optional<WeekendHouseOwner> weekendHouseOwner = findById(id);
        if (weekendHouseOwner.isPresent()) {
            weekendHouseOwner.get().setWantDeleting(true);
            saveWeekendHouseOwner(weekendHouseOwner.get());
        }
    }

    public List<WeekendHouse> findAllWeekendHouses() {
        return weekendHouseRepository.findAll();
    }
}
