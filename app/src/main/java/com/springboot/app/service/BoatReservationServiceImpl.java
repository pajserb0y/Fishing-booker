package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.BoatReservationDTO;
import com.springboot.app.model.dto.FishingLessonReservationDTO;
import com.springboot.app.model.dto.ReportDTO;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class BoatReservationServiceImpl implements BoatReservationService {
    private final BoatReservationRepository boatReservationRepository;
    private final BoatRepository boatRepository;
    private final CustomerRepository customerRepository;
    private final BoatFeedbackRepository boatFeedbackRepository;
    private final BoatComplaintRepository boatComplaintRepository;
    private final BoatOwnerRepository boatOwnerRepository;
    private final ReportRepository reportRepository;

    public BoatReservationServiceImpl( BoatReservationRepository boatReservationRepository, CustomerRepository customerRepository,
                                       BoatRepository boatRepository,BoatFeedbackRepository boatFeedbackRepository,
                                       BoatComplaintRepository boatComplaintRepository, BoatOwnerRepository boatOwnerRepository,
                                       ReportRepository reportRepository  ) {

        this.boatReservationRepository = boatReservationRepository;
        this.boatRepository = boatRepository;
        this.customerRepository = customerRepository;
        this.boatFeedbackRepository = boatFeedbackRepository;
        this.boatComplaintRepository = boatComplaintRepository;
        this.boatOwnerRepository = boatOwnerRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public BoatReservation reserve(BoatReservation boatReservation) {
        Optional<BoatReservation> resInDatabase = boatReservationRepository.findById(boatReservation.getId());
        if (resInDatabase.isPresent()) {        // rezervacija akcije
            if(resInDatabase.get().getCustomer() != null || resInDatabase.get().isCancelled())
                return null;
            resInDatabase.get().setCustomer(customerRepository.findById(boatReservation.getCustomer().getId()).get());
            resInDatabase.get().setPrice(boatReservation.getPrice());
            boatReservationRepository.save(resInDatabase.get());
            return resInDatabase.get();
        }
        else {
            Optional<Boat> boat = boatRepository.findById(boatReservation.getBoat().getId());
            if (boatReservation.getCustomer() != null) {
                Optional<Customer> customer = customerRepository.findById(boatReservation.getCustomer().getId());
                if (boat.isPresent() && customer.isPresent()) {     // standard rezervacija - KONFLIKTNA
                    if(boatReservationRepository.findAllForDateRange(boatReservation.getStartDateTime(), boatReservation.getEndDateTime()).contains(boat.get().getId()))
                        return null;
                    boatReservation.setBoat(boat.get());
                    boatReservation.setCustomer(customer.get());
                    boatReservationRepository.save(boatReservation);
                    boatReservationRepository.cancellAllActionsForThisDateRangeForThisBoat(boatReservation.getStartDateTime(),
                            boatReservation.getEndDateTime(), boat.get().getId());
                }
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
    public void sendReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public List<BoatReservation> getCurrentSpecialOffers() {
        return boatReservationRepository.getCurrentSpecialOffers();
    }

    @Override
    public List<BoatReservation> findAllReservationsForBoat(Boat boat) {
        return boatReservationRepository.findByBoat(boat);
    }
    
    public Set<BoatReservationDTO> getAllReservationsForBoatOwner(String username) {
        Integer id = boatOwnerRepository.findByUsername(username).getId();
        List<BoatReservation> boatReservations = boatReservationRepository.findAllReservationsForBoatOwner(id);
        Set<BoatReservationDTO> boatReservationDTOs = new HashSet<>();
        for (BoatReservation res : boatReservations) {
            BoatReservationDTO dto = new BoatReservationDTO(res);
            boatReservationDTOs.add(dto);
        }

        return boatReservationDTOs;
    }
}
