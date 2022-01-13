package com.springboot.app.controller;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.model.dto.WeekendHouseDTO;
import com.springboot.app.model.dto.WeekendHouseReservationDTO;
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
import java.util.Set;

@RestController
@RequestMapping("/api/weekendHouseReservations")
public class WeekendHouseReservationController {

    private final WeekendHouseReservationService weekendHouseReservationService;
    private final EmailService emailService;

    @Autowired
    public WeekendHouseReservationController(WeekendHouseReservationService weekendHouseReservationService, EmailService emailService) {
        this.weekendHouseReservationService = weekendHouseReservationService;
        this.emailService = emailService;
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/reserve")
    public ResponseEntity<?> reserve(@RequestBody WeekendHouseReservationDTO reservationDto) {
        WeekendHouseReservation reservation = weekendHouseReservationService.reserve(new WeekendHouseReservation(reservationDto));
        emailService.sendNotificationForWeekendHouseReservation(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(path = "/getFutureForCustomerUsername/{username}")
    public Set<WeekendHouseReservationDTO> getFutureForCustomerUsername(@PathVariable String username) {
        Set<WeekendHouseReservationDTO> reservationsDto = new HashSet<>();
        for (WeekendHouseReservation res : weekendHouseReservationService.getFutureForCustomerUsername(username))
            reservationsDto.add(new WeekendHouseReservationDTO(res));

        return reservationsDto;
    }
}
