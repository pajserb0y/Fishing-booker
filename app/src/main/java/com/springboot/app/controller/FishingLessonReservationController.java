package com.springboot.app.controller;

import com.springboot.app.model.FishingLessonComplaint;
import com.springboot.app.model.FishingLessonFeedback;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.dto.FishingLessonComplaintDTO;
import com.springboot.app.model.dto.FishingLessonFeedbackDTO;
import com.springboot.app.model.dto.FishingLessonReservationDTO;
import com.springboot.app.service.EmailService;
import com.springboot.app.service.FishingLessonReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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
    public ResponseEntity<?> reserve(@RequestBody FishingLessonReservationDTO reservationDto) {
        FishingLessonReservation reservation = fishingLessonReservationService.reserve(new FishingLessonReservation(reservationDto));
        emailService.sendNotificationForFishingLessonReservation(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
}
