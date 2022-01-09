package com.springboot.app.service;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.dto.BoatOwnerDTO;

public interface BoatOwnerService {
    public BoatOwner saveBoatOwner(BoatOwner boatOwner);

    BoatOwner findByUsername(String username);

    BoatOwner changeBoatOwner(BoatOwnerDTO boatOwnerDTO);
}
