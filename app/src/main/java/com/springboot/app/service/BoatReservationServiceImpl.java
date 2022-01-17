package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatReservationServiceImpl implements BoatReservationService {
    private final BoatReservationRepository boatReservationRepository;
    private final BoatRepository boatRepository;
    private final CustomerRepository customerRepository;
    private final BoatFeedbackRepository boatFeedbackRepository;
    private final BoatComplaintRepository boatComplaintRepository;

    public BoatReservationServiceImpl( BoatReservationRepository boatReservationRepository, CustomerRepository customerRepository,
                                       BoatRepository boatRepository,BoatFeedbackRepository boatFeedbackRepository,
                                       BoatComplaintRepository boatComplaintRepository     ) {
        this.boatReservationRepository = boatReservationRepository;
        this.boatRepository = boatRepository;
        this.customerRepository = customerRepository;
        this.boatFeedbackRepository = boatFeedbackRepository;
        this.boatComplaintRepository = boatComplaintRepository;
    }

    @Override
    public BoatReservation reserve(BoatReservation boatReservation) {
        Optional<BoatReservation> resInDatabase = boatReservationRepository.findById(boatReservation.getId());
        if (resInDatabase.isPresent()) {
            resInDatabase.get().setCustomer(customerRepository.findById(boatReservation.getCustomer().getId()).get());
            boatReservationRepository.save(resInDatabase.get());
            return resInDatabase.get();
        }
        else {
            Optional<Boat> boat = boatRepository.findById(boatReservation.getBoat().getId());
            Optional<Customer> customer = customerRepository.findById(boatReservation.getCustomer().getId());
            if (boat.isPresent() && customer.isPresent()) {
                boatReservation.setBoat(boat.get());
                boatReservation.setCustomer(customer.get());
                boatReservationRepository.save(boatReservation);
            }
            return boatReservation;
        }
    }

    @Override
    public List<BoatReservation> getFutureForCustomerUsername(String username) {
        return boatReservationRepository.findAllFutureReservationsByCustomerUsername(username);
    }

    @Override
    public List<BoatReservation> getPastForCustomerUsername(String username) {
        return boatReservationRepository.findAllPastReservationsByCustomerUsername(username);
    }

    @Override
    public void cancel(Integer id) {
        Optional<BoatReservation> res = boatReservationRepository.findById(id);
        if (res.isPresent()) {
            res.get().setCancelled(true);
            boatReservationRepository.save(res.get());
        }
    }

    @Override
    public Optional<BoatReservation> findById(Integer boatReservationId) {
        return boatReservationRepository.findById(boatReservationId);
    }

    @Override
    public void sendFeedback(BoatFeedback boatFeedback) {
        boatFeedbackRepository.save(boatFeedback);
    }

    @Override
    public void sendComplaint(BoatComplaint boatComplaint) {
        boatComplaintRepository.save(boatComplaint);
    }

    @Override
    public List<BoatReservation> getCurrentSpecialOffers() {
        return boatReservationRepository.getCurrentSpecialOffers();
    }
}
