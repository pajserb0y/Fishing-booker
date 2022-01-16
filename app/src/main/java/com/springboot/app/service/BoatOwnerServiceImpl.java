package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.BoatOwnerDTO;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BoatOwnerServiceImpl implements BoatOwnerService{

    private final BoatOwnerRepository boatOwnerRepository;
    private final RoleService roleService;
    private final BoatRepository boatRepository;
    private final BoatFeedbackRepository boatFeedbackRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final TermBoatRepository termBoatRepository;

    public BoatOwnerServiceImpl(BoatOwnerRepository boatOwnerRepository, RoleService roleService, BoatRepository boatRepository, TermBoatRepository termBoatRepository,
                                BoatFeedbackRepository boatFeedbackRepository, BoatReservationRepository boatReservationRepository) {
        this.boatOwnerRepository = boatOwnerRepository;
        this.roleService = roleService;
        this.boatRepository = boatRepository;
        this.boatFeedbackRepository = boatFeedbackRepository;
        this.boatReservationRepository = boatReservationRepository;
        this.termBoatRepository = termBoatRepository;
    }

    @Override
    public BoatOwner saveBoatOwner(BoatOwner boatOwner) {
        List<Role> roles = roleService.findByName("ROLE_BOAT_OWNER");
        boatOwner.setRole(roles.get(0));
        boatOwner.setActivated(true);       //admin treba da odobri aktivaciju naloga koji nisu customer
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
        return boatOwner;
    }

    @Override
    public Collection<String> findAllUsernames() {
        return boatOwnerRepository.findAllUsernames();
    }

    @Override
    public Optional<BoatOwner> findById(int id) {
        return boatOwnerRepository.findById(id);
    }

    @Override
    public void setWantedToDelete(int id) {
        Optional<BoatOwner> boatOwner = findById(id);
        if(boatOwner.isPresent()) {
            boatOwner.get().setWantDeleting(true);
            saveBoatOwner(boatOwner.get());
        }
    }

    @Override
    public boolean removeBoat(Integer boatId) {
        Optional<Boat> boat = boatRepository.findById(boatId);

        if (!boat.isPresent())
            return false;

        if (!boatReservationRepository.existsFutureReservation(boat.get().getId()).isEmpty())
            return false;

        boatRepository.delete(boat.get());
        return true;
    }

    @Override
    public List<Boat> findAllBoats() {
        return boatRepository.findAll();
    }

    @Override
    public Integer findAvgGradeForBoatId(Integer id) {
        return boatFeedbackRepository.findAverageGradeBoatByBoatId(id);
    }

    @Override
    public List<Boat> findAvailableBoatsForDateRange(DateTimeRangeDTO dateRange) {
        List<Integer> boatIds = boatReservationRepository.findAllForDateRange(dateRange.getStart(), dateRange.getEnd());
        boatIds.addAll(termBoatRepository.findAllBoatIdsThatWorkForPeriod(dateRange.getStart(), dateRange.getEnd()));
        if (boatIds.isEmpty())
            return findAllBoats();
        else
            return boatRepository.findAllByIdNotIn(boatIds);
    }

    @Override
    public Boat findBoatById(Integer id) {
        return boatRepository.findById(id).get();
    }

    @Override
    public List<TermBoat> findAllFreeTermsForBoat(Boat boat) {
        return termBoatRepository.findByBoat(boat);
    }
}
