package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import com.springboot.app.model.dto.WeekendHouseReservationDTO;
import com.springboot.app.repository.CustomerRepository;
import com.springboot.app.repository.WeekendHouseOwnerRepository;
import com.springboot.app.repository.WeekendHouseRepository;
import com.springboot.app.repository.WeekendHouseReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WeekendHouseReservationServiceImpl implements WeekendHouseReservationService {
    private final WeekendHouseReservationRepository weekendHouseReservationRepository;
    private final WeekendHouseRepository weekendHouseRepository;
    private final CustomerRepository customerRepository;

    public WeekendHouseReservationServiceImpl(WeekendHouseReservationRepository weekendHouseReservationRepository, CustomerRepository customerRepository,
                                              WeekendHouseRepository weekendHouseRepository) {
        this.weekendHouseReservationRepository = weekendHouseReservationRepository;
        this.weekendHouseRepository = weekendHouseRepository;
        this.customerRepository = customerRepository;
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
        return weekendHouseReservationRepository.findAllByCustomerUsername(username);
    }
}
