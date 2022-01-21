package com.springboot.app.controller;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.*;
import com.springboot.app.service.BoatOwnerService;
import com.springboot.app.service.BoatReservationService;
import com.springboot.app.service.EmailService;
import com.springboot.app.service.WeekendHouseReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/boatReservations")
public class BoatReservationController {
    private final BoatReservationService boatReservationService;
    private final BoatOwnerService boatOwnerService;
    private final EmailService emailService;

    @Autowired
    public BoatReservationController(BoatReservationService boatReservationService,BoatOwnerService boatOwnerService, EmailService emailService) {
        this.boatReservationService = boatReservationService;
        this.emailService = emailService;
        this.boatOwnerService = boatOwnerService;
    }


    @PreAuthorize("hasAnyRole('CUSTOMER','BOAT_OWNER')")
    @PostMapping(path = "/reserve")
    public Set<BoatReservationDTO> reserve(@RequestBody BoatReservationDTO reservationDto) {
        BoatReservation reservation = boatReservationService.reserve(new BoatReservation(reservationDto));
        if(reservation == null)     //neko je pre njega rezervisao u preklapajucem terminu i on ne moze
            return null;
        if(reservation.getCustomer() != null)
            emailService.sendNotificationForBoatReservation(reservation);
        else
            for (Customer customer : reservation.getBoat().getSubscribedCustomers())
                emailService.sendNotificationForSpecialOfferBoat(customer, reservation);

        List<BoatReservation> boatReservations = boatReservationService.findAllReservationsForBoat(reservation.getBoat());
        Set<BoatReservationDTO> resDtos = new HashSet<>();
        for (BoatReservation res : boatReservations)
        {
            resDtos.add(new BoatReservationDTO(res));
        }
        return resDtos;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getFutureForCustomerUsername/{username}")
    public Set<BoatReservationDTO> getFutureForCustomerUsername(@PathVariable String username) {
        Set<BoatReservationDTO> reservationsDto = new HashSet<>();
        for (BoatReservation res : boatReservationService.getFutureForCustomerUsername(username))
            reservationsDto.add(new BoatReservationDTO(res));

        return reservationsDto;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getPastForCustomerUsername/{username}")
    public Set<BoatReservationDTO> getPastForCustomerUsername(@PathVariable String username) {
        Set<BoatReservationDTO> reservationsDto = new HashSet<>();
        for (BoatReservation res : boatReservationService.getPastForCustomerUsername(username))
            reservationsDto.add(new BoatReservationDTO(res));

        return reservationsDto;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Integer id) {
        boatReservationService.cancel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/sendFeedback")
    public ResponseEntity<?> sendFeedback(@RequestBody BoatFeedbackDTO boatFeedbackDTO) {
        Optional<BoatReservation> res = boatReservationService.findById(boatFeedbackDTO.getBoatReservationId());
        boatReservationService.sendFeedback(new BoatFeedback(boatFeedbackDTO, res.get()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/sendComplaint")
    public ResponseEntity<?> sendComplaint(@RequestBody BoatComplaintDTO complaintDTO) {
        Optional<BoatReservation> res = boatReservationService.findById(complaintDTO.getBoatReservationId());
        boatReservationService.sendComplaint(new BoatComplaint(complaintDTO, res.get()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @PostMapping(path = "/reportCustomer")
    public ResponseEntity<?> reportCustomer(@RequestBody ReportDTO reportDTO) {
        Optional<BoatReservation> reservation = boatReservationService.findById(reportDTO.getReservationId());

        if (!reservation.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Report report = new Report(reportDTO, reservation.get().getBoat().getBoatOwner().getId(), reservation.get().getCustomer().getId(), reservation.get().getBoat().getId(), "fishing");
        boatReservationService.sendReport(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @GetMapping(path = "/getAllForBoatOwner/{username}")
    public Set<BoatReservationDTO> getAllForBoatOwner(@PathVariable String username) {
        return boatReservationService.getAllReservationsForBoatOwner(username);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getCurrentSpecialOffers")
    public Set<BoatReservationDTO> getCurrentSpecialOffers() {
        Set<BoatReservationDTO> reservationsDto = new HashSet<>();
        for (BoatReservation res : boatReservationService.getCurrentSpecialOffers())
            reservationsDto.add(new BoatReservationDTO(res));

        return reservationsDto;
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @GetMapping(path = "/getAllReservationsForBoat/{id}")
    public Set<BoatReservationDTO> getAllReservationsForBoat(@PathVariable Integer id) {
        Boat boat = boatOwnerService.findBoatById(id);
        List<BoatReservation> boatReservations = boatReservationService.findAllReservationsForBoat(boat);
        Set<BoatReservationDTO> boatReservationDTOS = new HashSet<>();
        for (BoatReservation res : boatReservations)
            boatReservationDTOS.add(new BoatReservationDTO(res));
        return boatReservationDTOS;
    }
}
