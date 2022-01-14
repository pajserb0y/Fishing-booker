package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseFeedback;
import com.springboot.app.model.WeekendHouseReservation;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeekendHouseReservationServiceImpl implements WeekendHouseReservationService {
    private final WeekendHouseReservationRepository weekendHouseReservationRepository;
    private final WeekendHouseRepository weekendHouseRepository;
    private final CustomerRepository customerRepository;
    private final WeekendHouseFeedbackRepository weekendHouseFeedbackRepository;

    public WeekendHouseReservationServiceImpl(WeekendHouseReservationRepository weekendHouseReservationRepository, CustomerRepository customerRepository,
                                              WeekendHouseRepository weekendHouseRepository, WeekendHouseFeedbackRepository weekendHouseFeedbackRepository) {
        this.weekendHouseReservationRepository = weekendHouseReservationRepository;
        this.weekendHouseRepository = weekendHouseRepository;
        this.customerRepository = customerRepository;
        this.weekendHouseFeedbackRepository = weekendHouseFeedbackRepository;
    }


    @Override
    public WeekendHouseReservation reserve(WeekendHouseReservation weekendHouseReservation) {
        Optional<WeekendHouse> house = weekendHouseRepository.findById(weekendHouseReservation.getWeekendHouse().getId());
        Optional<Customer> customer = customerRepository.findById(weekendHouseReservation.getCustomer().getId());
        if (house.isPresent() && customer.isPresent()) {
            weekendHouseReservation.setWeekendHouse(house.get());
            weekendHouseReservation.setCustomer(customer.get());
            weekendHouseReservationRepository.save(weekendHouseReservation);
        }
        return weekendHouseReservation;
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
}
