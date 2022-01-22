package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.*;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<Boat> findAllBoatForOwner(BoatOwner boatOwner) {
        return boatRepository.findByBoatOwner(boatOwner);
    }

    @Override
    public TermBoat addFreeTerm(TermBoat term) {
        List<BoatReservation> boatReservations = boatReservationRepository.findByBoat(term.getBoat());
        for(BoatReservation res : boatReservations)
        {
            if(res.getStartDateTime().getDate() <= term.getStartDateTime().getDate() && term.getStartDateTime().getDate() <= res.getEndDateTime().getDate() )
                return null;
            if(res.getStartDateTime().getDate() <= term.getEndDateTime().getDate() && term.getEndDateTime().getDate() <= res.getEndDateTime().getDate() )
                return null;
            if(term.getStartDateTime().getDate() <= res.getStartDateTime().getDate() && res.getEndDateTime().getDate() <= term.getEndDateTime().getDate() )
                return null;
        }
        termBoatRepository.save(term);
        return term;
    }

    @Override
    public Boat changeBoat(BoatDTO boatDTO) {
        Optional<Boat> boat = boatRepository.findById(boatDTO.getId());
        boat.get().setName(boatDTO.getName());
        boat.get().setAddress(boatDTO.getAddress());
        boat.get().setEngineNumber(boatDTO.getEngineNumber());
        boat.get().setHorsePower(boatDTO.getHorsePower());
        boat.get().setMaxSpeed(boatDTO.getMaxSpeed());
        boat.get().setLength(boatDTO.getLength());
        boat.get().setImagePath(boatDTO.getImagePath());
        boat.get().setDescription(boatDTO.getDescription());
        boat.get().setCapacity(boatDTO.getCapacity());
        Set<TermBoat> terms = new HashSet<>();
        for (TermBoatDTO termDto : boatDTO.getFreeTerms())
            terms.add(new TermBoat(termDto));
        boat.get().setFreeTerms(terms);
        boat.get().setRules(boatDTO.getRules());
        boat.get().setPrice(boatDTO.getPrice());
        Set<BoatReservation> reservations = new HashSet<>();
        for (BoatReservationDTO res : boatDTO.getBoatReservations())
            reservations.add(new BoatReservation(res));
        boat.get().setBoatReservations(reservations);
        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : boatDTO.getAdditionalServices())
            services.add(new AdditionalService(service));
        boat.get().setAdditionalServices(services);
        boat.get().setBoatOwner(new BoatOwner(boatDTO.getBoatOwner()));
        saveBoat(boat.get());

        return boat.get();

    }

    public void saveBoat(Boat boat) {
        boatRepository.save(boat);
    }
}
