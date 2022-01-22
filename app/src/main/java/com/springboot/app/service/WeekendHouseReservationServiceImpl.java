package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.WeekendHouseReservationDTO;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockTimeoutException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class WeekendHouseReservationServiceImpl implements WeekendHouseReservationService {
    private final WeekendHouseReservationRepository weekendHouseReservationRepository;
    private final WeekendHouseRepository weekendHouseRepository;
    private final CustomerRepository customerRepository;
    private final WeekendHouseFeedbackRepository weekendHouseFeedbackRepository;
    private final WeekendHouseComplaintRepository weekendHouseComplaintRepository;
    private final WeekendHouseOwnerRepository weekendHouseOwnerRepository;
    private final ReportRepository reportRepository;

    public WeekendHouseReservationServiceImpl(WeekendHouseReservationRepository weekendHouseReservationRepository, CustomerRepository customerRepository,
                                              WeekendHouseRepository weekendHouseRepository, WeekendHouseFeedbackRepository weekendHouseFeedbackRepository,
                                              WeekendHouseComplaintRepository weekendHouseComplaintRepository, WeekendHouseOwnerRepository weekendHouseOwnerRepository,
                                              ReportRepository reportRepository) {
        this.weekendHouseReservationRepository = weekendHouseReservationRepository;
        this.weekendHouseRepository = weekendHouseRepository;
        this.customerRepository = customerRepository;
        this.weekendHouseFeedbackRepository = weekendHouseFeedbackRepository;
        this.weekendHouseComplaintRepository = weekendHouseComplaintRepository;
        this.weekendHouseOwnerRepository = weekendHouseOwnerRepository;
        this.reportRepository = reportRepository;
    }


    @Override
    public WeekendHouseReservation reserve(WeekendHouseReservation weekendHouseReservation) {
        try {
            Optional<WeekendHouseReservation> resInDatabase = weekendHouseReservationRepository.findOneById(weekendHouseReservation.getId());

            if (resInDatabase.isPresent()) {         // rezervacija akcije - KONFLIKTNA
                if(resInDatabase.get().getCustomer() != null || resInDatabase.get().isCancelled())
                    return null;
                resInDatabase.get().setCustomer(customerRepository.findById(weekendHouseReservation.getCustomer().getId()).get());
                resInDatabase.get().setPrice(weekendHouseReservation.getPrice());
                weekendHouseReservationRepository.save(resInDatabase.get());
                return resInDatabase.get();
            }
            else {
                Optional<WeekendHouse> house = weekendHouseRepository.findById(weekendHouseReservation.getWeekendHouse().getId());
                if (weekendHouseReservation.getCustomer() != null) {
                    Optional<Customer> customer = customerRepository.findById(weekendHouseReservation.getCustomer().getId());
                    if (house.isPresent() && customer.isPresent()) {        // standard rezervacija - KONFLIKTNA
                        if (weekendHouseReservationRepository.findAllForDateRange(weekendHouseReservation.getStartDateTime(), weekendHouseReservation.getEndDateTime()).contains(house.get().getId()))
                            return null;
                        weekendHouseReservation.setWeekendHouse(house.get());
                        weekendHouseReservation.setCustomer(customer.get());
                        weekendHouseReservationRepository.save(weekendHouseReservation);
                        weekendHouseReservationRepository.cancellAllActionsForThisDateRangeForThisWeekendHouse(weekendHouseReservation.getStartDateTime(),
                                weekendHouseReservation.getEndDateTime(), house.get().getId());
                    }
                } else {
                    if (house.isPresent()) {        //pravljenje akcije, nije konfliktno jer samo house owner to moze da radi
                        weekendHouseReservation.setWeekendHouse(house.get());
                        weekendHouseReservation.setCustomer(null);
                        weekendHouseReservationRepository.save(weekendHouseReservation);
                    }
                }
                return weekendHouseReservation;
            }
        } catch (LockTimeoutException e) { return null; }
    }

    @Override
    public List<WeekendHouseReservation> getFutureForCustomerUsername(String username) {
        return weekendHouseReservationRepository.findAllFutureReservationsByCustomerUsername(username);
    }

    @Override
    public List<WeekendHouseReservation> getPastForCustomerUsername(String username) {
        return weekendHouseReservationRepository.findAllPastReservationsByCustomerUsername(username);
    }

    @Override
    public void cancel(Integer id) {
        Optional<WeekendHouseReservation> res = weekendHouseReservationRepository.findById(id);
        if (res.isPresent()) {
            res.get().setCancelled(true);
            weekendHouseReservationRepository.save(res.get());
        }
    }

    @Override
    public Optional<WeekendHouseReservation> findById(Integer weekendHouseReservationId) {
        return weekendHouseReservationRepository.findById(weekendHouseReservationId);
    }

    @Override
    public void sendFeedback(WeekendHouseFeedback weekendHouseFeedback) {
        weekendHouseFeedbackRepository.save(weekendHouseFeedback);
    }

    @Override
    public void sendComplaint(WeekendHouseComplaint weekendHouseComplaint) {
        weekendHouseComplaintRepository.save(weekendHouseComplaint);
    }

    @Override
    public void sendReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public List<WeekendHouseReservation> getCurrentSpecialOffers() {
        return weekendHouseReservationRepository.getCurrentSpecialOffers();
    }

    @Override
    public Set<WeekendHouseReservationDTO> getAllReservationsForWeekendHouseOwner(String username) {
        Integer id = weekendHouseOwnerRepository.findByUsername(username).getId();
        List<WeekendHouseReservation> weekendHouseReservations = weekendHouseReservationRepository.findAllReservationsForWeekendHouseOwner(id);
        Set<WeekendHouseReservationDTO> weekendHouseReservationsDTOs = new HashSet<>();
        for (WeekendHouseReservation res : weekendHouseReservations) {
            WeekendHouseReservationDTO dto = new WeekendHouseReservationDTO(res);
            weekendHouseReservationsDTOs.add(dto);
        }

        return weekendHouseReservationsDTOs;
    }

    @Override
    public List<WeekendHouseReservation> findAllReservationsForWeekendHouse(WeekendHouse weekendHouse) {
        return weekendHouseReservationRepository.findByWeekendHouse(weekendHouse);
    }
}
