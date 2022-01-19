package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WeekendHouseReservationServiceImpl implements WeekendHouseReservationService {
    private final WeekendHouseReservationRepository weekendHouseReservationRepository;
    private final WeekendHouseRepository weekendHouseRepository;
    private final CustomerRepository customerRepository;
    private final WeekendHouseFeedbackRepository weekendHouseFeedbackRepository;
    private final WeekendHouseComplaintRepository weekendHouseComplaintRepository;

    public WeekendHouseReservationServiceImpl(WeekendHouseReservationRepository weekendHouseReservationRepository, CustomerRepository customerRepository,
                                              WeekendHouseRepository weekendHouseRepository, WeekendHouseFeedbackRepository weekendHouseFeedbackRepository,
                                              WeekendHouseComplaintRepository weekendHouseComplaintRepository) {
        this.weekendHouseReservationRepository = weekendHouseReservationRepository;
        this.weekendHouseRepository = weekendHouseRepository;
        this.customerRepository = customerRepository;
        this.weekendHouseFeedbackRepository = weekendHouseFeedbackRepository;
        this.weekendHouseComplaintRepository = weekendHouseComplaintRepository;
    }


    @Override
    public WeekendHouseReservation reserve(WeekendHouseReservation weekendHouseReservation) {
        Optional<WeekendHouseReservation> resInDatabase = weekendHouseReservationRepository.findById(weekendHouseReservation.getId());
        if (resInDatabase.isPresent()) {         // rezervacija akcije
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
                    if(weekendHouseReservationRepository.findAllForDateRange(weekendHouseReservation.getStartDateTime(), weekendHouseReservation.getEndDateTime()).contains(house.get().getId()))
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
    public void sendFeedback(WeekendHouseFeedback weekendHouseFeedback) {
        weekendHouseFeedbackRepository.save(weekendHouseFeedback);
    }

    @Override
    public Optional<WeekendHouseReservation> findById(Integer weekendHouseReservationId) {
        return weekendHouseReservationRepository.findById(weekendHouseReservationId);
    }

    @Override
    public void sendComplaint(WeekendHouseComplaint weekendHouseComplaint) {
        weekendHouseComplaintRepository.save(weekendHouseComplaint);
    }

    @Override
    public List<WeekendHouseReservation> getCurrentSpecialOffers() {
        return weekendHouseReservationRepository.getCurrentSpecialOffers();
    }

    @Override
    public List<WeekendHouseReservation> findAllReservationsForWeekendHouse(WeekendHouse weekendHouse) {
        return weekendHouseReservationRepository.findByWeekendHouse(weekendHouse);
    }
}
