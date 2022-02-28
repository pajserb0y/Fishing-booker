package com.springboot.app.controller;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.*;
import com.springboot.app.service.EmailService;
import com.springboot.app.service.WeekendHouseOwnerService;
import com.springboot.app.service.WeekendHouseReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/weekendHouseReservations")
public class WeekendHouseReservationController {

    private final WeekendHouseReservationService weekendHouseReservationService;
    private final WeekendHouseOwnerService weekendHouseOwnerService;
    private final EmailService emailService;

    @Autowired
    public WeekendHouseReservationController(WeekendHouseReservationService weekendHouseReservationService,WeekendHouseOwnerService weekendHouseOwnerService, EmailService emailService) {
        this.weekendHouseReservationService = weekendHouseReservationService;
        this.emailService = emailService;
        this.weekendHouseOwnerService = weekendHouseOwnerService;
    }


    @PreAuthorize("hasAnyRole('CUSTOMER','WEEKEND_HOUSE_OWNER')")
    @PostMapping(path = "/reserve")
    public Set<WeekendHouseReservationDTO> reserve(@RequestBody WeekendHouseReservationDTO reservationDto) {
        WeekendHouseReservation reservation = weekendHouseReservationService.reserve(new WeekendHouseReservation(reservationDto));
        if(reservation == null)     //neko je pre njega rezervisao u preklapajucem terminu i on ne moze
            return null;
        if(reservation.getCustomer() != null)
            emailService.sendNotificationForWeekendHouseReservation(reservation);
        else
            for (Customer customer : reservation.getWeekendHouse().getSubscribedCustomers())
                emailService.sendNotificationForSpecialOfferWeekendHouse(customer, reservation);

        List<WeekendHouseReservation> weekendHouseReservations = weekendHouseReservationService.findAllReservationsForWeekendHouse(reservation.getWeekendHouse());
        Set<WeekendHouseReservationDTO> resDtos = new HashSet<>();
        for (WeekendHouseReservation res : weekendHouseReservations)
        {
            resDtos.add(new WeekendHouseReservationDTO(res));
        }
        return resDtos;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getFutureForCustomerUsername/{username}")
    public Set<WeekendHouseReservationDTO> getFutureForCustomerUsername(@PathVariable String username) {
        Set<WeekendHouseReservationDTO> reservationsDto = new HashSet<>();
        for (WeekendHouseReservation res : weekendHouseReservationService.getFutureForCustomerUsername(username))
            reservationsDto.add(new WeekendHouseReservationDTO(res));

        return reservationsDto;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getPastForCustomerUsername/{username}")
    public Set<WeekendHouseReservationDTO> getPastForCustomerUsername(@PathVariable String username) {
        Set<WeekendHouseReservationDTO> reservationsDto = new HashSet<>();
        for (WeekendHouseReservation res : weekendHouseReservationService.getPastForCustomerUsername(username))
            reservationsDto.add(new WeekendHouseReservationDTO(res));

        return reservationsDto;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Integer id) {
        weekendHouseReservationService.cancel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/sendFeedback")
    public ResponseEntity<?> sendFeedback(@RequestBody WeekendHouseFeedbackDTO weekendHouseFeedbackDTO) {
        Optional<WeekendHouseReservation> res = weekendHouseReservationService.findById(weekendHouseFeedbackDTO.getWeekendHouseReservationId());
        weekendHouseReservationService.sendFeedback(new WeekendHouseFeedback(weekendHouseFeedbackDTO, res.get()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/sendComplaint")
    public ResponseEntity<?> sendComplaint(@RequestBody WeekendHouseComplaintDTO complaintDTO) {
        Optional<WeekendHouseReservation> res = weekendHouseReservationService.findById(complaintDTO.getWeekendHouseReservationId());
        weekendHouseReservationService.sendComplaint(new WeekendHouseComplaint(complaintDTO, res.get()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @PostMapping(path = "/reportCustomer")
    public ResponseEntity<?> reportCustomer(@RequestBody ReportDTO reportDTO) {
        Optional<WeekendHouseReservation> reservation = weekendHouseReservationService.findById(reportDTO.getReservationId());

        if (!reservation.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Report report = new Report(reportDTO, reservation.get().getWeekendHouse().getWeekendHouseOwner().getId(), reservation.get().getCustomer().getId(), reservation.get().getWeekendHouse().getId(), "fishing");
        weekendHouseReservationService.sendReport(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @GetMapping(path = "/getAllReservationsForWeekendHouse/{id}")
    public Set<WeekendHouseReservationDTO> getAllReservationsForWeekendHouse(@PathVariable Integer id) {
        WeekendHouse weekendHouse = weekendHouseOwnerService.findWeekendHouseById(id);
        List<WeekendHouseReservation> weekendHouseReservations = weekendHouseReservationService.findAllReservationsForWeekendHouse(weekendHouse);
        Set<WeekendHouseReservationDTO> weekendHouseReservationDTOs = new HashSet<>();
        for (WeekendHouseReservation res : weekendHouseReservations)
            weekendHouseReservationDTOs.add(new WeekendHouseReservationDTO(res));
        return weekendHouseReservationDTOs;
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @GetMapping(path = "/getAllForWeekendHouseOwner/{username}")
    public Set<WeekendHouseReservationDTO> getAllForWeekendHouseOwner(@PathVariable String username) {
        return weekendHouseReservationService.getAllReservationsForWeekendHouseOwner(username);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getCurrentSpecialOffers")
    public Set<WeekendHouseReservationDTO> getCurrentSpecialOffers() {
        Set<WeekendHouseReservationDTO> reservationsDto = new HashSet<>();
        for (WeekendHouseReservation res : weekendHouseReservationService.getCurrentSpecialOffers())
            reservationsDto.add(new WeekendHouseReservationDTO(res));

        return reservationsDto;
    }
}
