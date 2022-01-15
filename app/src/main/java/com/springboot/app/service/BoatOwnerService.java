package com.springboot.app.service;

import com.springboot.app.model.Boat;
import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.TermBoat;
import com.springboot.app.model.dto.BoatOwnerDTO;
import com.springboot.app.model.dto.DateTimeRangeDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BoatOwnerService {
    public BoatOwner saveBoatOwner(BoatOwner boatOwner);

    BoatOwner findByUsername(String username);

    BoatOwner changeBoatOwner(BoatOwnerDTO boatOwnerDTO);

    Collection<String> findAllUsernames();

    Optional<BoatOwner> findById(int id);

    void setWantedToDelete(int id);

    List<Boat> findAllBoats();

    Integer findAvgGradeForBoatId(Integer id);

    List<Boat> findAvailableBoatsForDateRange(DateTimeRangeDTO dateRange);

    Boat findBoatById(Integer id);

    List<TermBoat> findAllFreeTermsForBoat(Boat boat);
}
