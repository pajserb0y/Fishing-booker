package com.springboot.app.service;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.dto.BoatOwnerDTO;

import java.util.Collection;
import java.util.Optional;

public interface BoatOwnerService {
    public BoatOwner saveBoatOwner(BoatOwner boatOwner);

    BoatOwner findByUsername(String username);

    BoatOwner changeBoatOwner(BoatOwnerDTO boatOwnerDTO);

    Collection<String> findAllUsernames();

    Optional<BoatOwner> findById(int id);

    void setWantedToDelete(int id);
}
