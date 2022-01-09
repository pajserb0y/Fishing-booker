package com.springboot.app.service;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.Customer;
import com.springboot.app.model.Role;
import com.springboot.app.model.dto.BoatOwnerDTO;
import com.springboot.app.repository.BoatOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoatOwnerServiceImpl implements BoatOwnerService{

    private final BoatOwnerRepository boatOwnerRepository;
    private final RoleService roleService;

    public BoatOwnerServiceImpl(BoatOwnerRepository boatOwnerRepository, RoleService roleService) {
        this.boatOwnerRepository = boatOwnerRepository;
        this.roleService = roleService;
    }

    @Override
    public BoatOwner saveBoatOwner(BoatOwner boatOwner) {
        List<Role> roles = roleService.findByName("ROLE_BOAT_OWNER");
        boatOwner.setRole(roles.get(0));
        boatOwnerRepository.save(boatOwner);
        return null;
    }

    @Override
    public BoatOwner findByUsername(String username) {
        return boatOwnerRepository.findByUsername(username);
    }

    @Override
    public BoatOwner changeBoatOwner(BoatOwnerDTO boatOwnerDTO) {
        BoatOwner boatOwner = findByUsername(boatOwnerDTO.getUsername());

        boatOwner.setPhone(boatOwnerDTO.getPhone());
        boatOwner.setCountry(boatOwnerDTO.getCountry());
        boatOwner.setCity(boatOwnerDTO.getCity());
        boatOwner.setAddress(boatOwnerDTO.getAddress());
        boatOwner.setLastName(boatOwnerDTO.getLastName());
        boatOwner.setFirstName(boatOwnerDTO.getFirstName());
        boatOwner.setMotive(boatOwnerDTO.getMotive());

        saveBoatOwner(boatOwner);
        return null;
    }
}
