package com.springboot.app.controller;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.*;
import com.springboot.app.service.EmailService;
import com.springboot.app.service.FishingLessonReservationService;
import com.springboot.app.service.InstructorService;
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
@RequestMapping("/api/fishingLessonReservations")
public class FishingLessonReservationController {
    private final FishingLessonReservationService fishingLessonReservationService;
    private final EmailService emailService;

    @Autowired
    public FishingLessonReservationController(FishingLessonReservationService fishingLessonReservationService, EmailService emailService) {
        this.fishingLessonReservationService = fishingLessonReservationService;
        this.emailService = emailService;
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/reserve")
    public Set<FishingLessonReservationDTO> reserve(@RequestBody FishingLessonReservationDTO reservationDto) {
        FishingLessonReservation reservation = fishingLessonReservationService.reserve(new FishingLessonReservation(reservationDto));
        if(reservation == null)     //neko je pre njega rezervisao u preklapajucem terminu i on ne moze
            return null;
        if(reservation.getCustomer() != null)
            emailService.sendNotificationForFishingLessonReservation(reservation);
        else
            for (Customer customer : reservation.getFishingLesson().getSubscribedCustomers())
                emailService.sendNotificationForSpecialOfferFishingLesson(customer, reservation);

        List<FishingLessonReservation> fishingLessonReservations = fishingLessonReservationService.findAllReservationsForFishingLesson(reservation.getFishingLesson());
        Set<FishingLessonReservationDTO> resDtos = new HashSet<>();
        for (FishingLessonReservation res : fishingLessonReservations)
        {
            resDtos.add(new FishingLessonReservationDTO(res));
        }
        return resDtos;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getFutureForCustomerUsername/{username}")
    public Set<FishingLessonReservationDTO> getFutureForCustomerUsername(@PathVariable String username) {
        Set<FishingLessonReservationDTO> reservationsDto = new HashSet<>();
        for (FishingLessonReservation res : fishingLessonReservationService.getFutureForCustomerUsername(username))
            reservationsDto.add(new FishingLessonReservationDTO(res));

        return reservationsDto;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getPastForCustomerUsername/{username}")
    public Set<FishingLessonReservationDTO> getPastForCustomerUsername(@PathVariable String username) {
        Set<FishingLessonReservationDTO> reservationsDto = new HashSet<>();
        for (FishingLessonReservation res : fishingLessonReservationService.getPastForCustomerUsername(username))
            reservationsDto.add(new FishingLessonReservationDTO(res));

        return reservationsDto;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Integer id) {
        fishingLessonReservationService.cancel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/sendFeedback")
    public ResponseEntity<?> sendFeedback(@RequestBody FishingLessonFeedbackDTO fishingLessonFeedbackDTO) {
        Optional<FishingLessonReservation> res = fishingLessonReservationService.findById(fishingLessonFeedbackDTO.getFishingLessonReservationId());
        fishingLessonReservationService.sendFeedback(new FishingLessonFeedback(fishingLessonFeedbackDTO, res.get()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/sendComplaint")
    public ResponseEntity<?> sendComplaint(@RequestBody FishingLessonComplaintDTO complaintDTO) {
        Optional<FishingLessonReservation> res = fishingLessonReservationService.findById(complaintDTO.getFishingLessonReservationId());
        fishingLessonReservationService.sendComplaint(new FishingLessonComplaint(complaintDTO, res.get()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping(path = "/reportCustomer")
    public ResponseEntity<?> reportCustomer(@RequestBody ReportDTO reportDTO) {
        Optional<FishingLessonReservation> reservation = fishingLessonReservationService.findById(reportDTO.getReservationId());

        if (!reservation.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Report report = new Report(reportDTO, reservation.get().getFishingLesson().getInstructor().getId(), reservation.get().getCustomer().getId(), reservation.get().getFishingLesson().getId(), "fishing");
        fishingLessonReservationService.sendReport(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping(path = "/getAllForInstructor/{username}")
    public Set<FishingLessonReservationDTO> getAllForInstructor(@PathVariable String username) {
        return fishingLessonReservationService.getAllReservationsForInstructor(username);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getCurrentSpecialOffers")
    public Set<FishingLessonReservationDTO> getCurrentSpecialOffers() {
        Set<FishingLessonReservationDTO> reservationsDto = new HashSet<>();
        for (FishingLessonReservation res : fishingLessonReservationService.getCurrentSpecialOffers())
            reservationsDto.add(new FishingLessonReservationDTO(res));

        return reservationsDto;
    }
}
