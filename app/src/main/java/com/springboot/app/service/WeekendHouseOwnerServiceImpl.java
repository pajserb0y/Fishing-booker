package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.*;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeekendHouseOwnerServiceImpl implements WeekendHouseOwnerService{
    private final WeekendHouseOwnerRepository weekendHouseOwnerRepository;
    private final WeekendHouseRepository weekendHouseRepository;
    private final WeekendHouseReservationRepository weekendHouseReservationRepository;
    private final RoleService roleService;
    private final TermRepository termRepository;
    private final WeekendHouseFeedbackRepository weekendHouseFeedbackRepository;

    public WeekendHouseOwnerServiceImpl(WeekendHouseOwnerRepository weekendHouseOwnerRepository, WeekendHouseRepository weekendHouseRepository,
                                        RoleService roleService, WeekendHouseReservationRepository weekendHouseReservationRepository, TermRepository termRepository,
                                        WeekendHouseFeedbackRepository weekendHouseFeedbackRepository) {
        this.weekendHouseOwnerRepository = weekendHouseOwnerRepository;
        this.roleService = roleService;
        this.weekendHouseRepository = weekendHouseRepository;
        this.weekendHouseReservationRepository = weekendHouseReservationRepository;
        this.termRepository = termRepository;
        this.weekendHouseFeedbackRepository = weekendHouseFeedbackRepository;
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
        return weekendHouseOwner;
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

    @Override
    public List<WeekendHouse> findAvailableHousesForDateRange(DateTimeRangeDTO dateRange) {
        List<Integer> weekendHouseIds = weekendHouseReservationRepository.findAllForDateRange(dateRange.getStart(), dateRange.getEnd());
        weekendHouseIds.addAll(termRepository.findAllHouseIdsThatWorkForPeriod(dateRange.getStart(), dateRange.getEnd()));
        if (weekendHouseIds.isEmpty())
            return findAllWeekendHouses();
        else
            return weekendHouseRepository.findAllByIdNotIn(weekendHouseIds);
    }

    @Override
    public List<WeekendHouse> findallWeekendHousesForOwner(WeekendHouseOwner weekendHouseOwner) {
        return weekendHouseRepository.findByWeekendHouseOwner(weekendHouseOwner);
    }

    @Override
    public WeekendHouse changeWeekendHouse(WeekendHouseDTO weekendHouseDTO) {
        Optional<WeekendHouse> weekendHouse = weekendHouseRepository.findById(weekendHouseDTO.getId());

        weekendHouse.get().setName(weekendHouseDTO.getName());
        weekendHouse.get().setAddress(weekendHouseDTO.getAddress());
        weekendHouse.get().setDescription(weekendHouseDTO.getDescription());
        weekendHouse.get().setBedNumber(weekendHouseDTO.getBedNumber());
        Set<Term> terms = new HashSet<>();
        for (TermDto termDto : weekendHouseDTO.getFreeTerms())
            terms.add(new Term(termDto));
        weekendHouse.get().setFreeTerms(terms);
        weekendHouse.get().setRules(weekendHouseDTO.getRules());
        weekendHouse.get().setPrice(weekendHouseDTO.getPrice());
        Set<WeekendHouseReservation> reservations = new HashSet<>();
        for (WeekendHouseReservationDTO res : weekendHouseDTO.getWeekendHouseReservations())
            reservations.add(new WeekendHouseReservation(res));
        weekendHouse.get().setWeekendHouseReservations(reservations);
        Set<AdditionalService> services = new HashSet<>();
        for (AdditionalServiceDTO service : weekendHouseDTO.getAdditionalServices())
            services.add(new AdditionalService(service));
        weekendHouse.get().setAdditionalServices(services);
        weekendHouse.get().setWeekendHouseOwner(new WeekendHouseOwner(weekendHouseDTO.getWeekendHouseOwner()));
        saveWeekendHouse(weekendHouse.get());

        return weekendHouse.get();
    }

    @Override
    public List<Term> findAllFreeTermsForWeekendHouse(WeekendHouse weekendHouse) {
        return termRepository.findByWeekendHouse(weekendHouse);
    }

    @Override
    public WeekendHouse findWeekendHouseById(Integer id) {
        return weekendHouseRepository.findById(id).get();
    }

    @Override
    public Integer findAvgGradeForHouseId(Integer id) {
        return weekendHouseFeedbackRepository.findAverageGradeHouseByWeekendHouseId(id);
    }

    @Override
    public void addFreeTerm(Term term) {
        termRepository.save(term);
    }
}
