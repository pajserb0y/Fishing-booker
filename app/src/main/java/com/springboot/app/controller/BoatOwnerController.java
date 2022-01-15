package com.springboot.app.controller;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.*;
import com.springboot.app.service.BoatOwnerService;
import com.springboot.app.service.EmailService;
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
@RequestMapping("/api/boatowners")
public class BoatOwnerController {
    private final BoatOwnerService boatOwnerService;
    private final EmailService emailService;
    @Autowired
    public BoatOwnerController(BoatOwnerService boatOwnerService, EmailService emailService) {
        this.boatOwnerService = boatOwnerService;
        this.emailService = emailService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createBoatOwner(@RequestBody @Valid BoatOwnerDTO boatOwnerDTO, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        BoatOwner newBoatOwner = boatOwnerService.saveBoatOwner(new BoatOwner(boatOwnerDTO));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('BOAT_OWNER')")
    @GetMapping(path = "/{username}")
    public BoatOwnerDTO getBoatOwnerByUsername(@PathVariable String username) {
        BoatOwner boatOwner = boatOwnerService.findByUsername(username);
        return new BoatOwnerDTO(boatOwner);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @PostMapping(path = "/edit")
    public BoatOwnerDTO editBoatOwner(@RequestBody BoatOwnerDTO boatOwnerDTO) {
        BoatOwner editedBoatOwner = boatOwnerService.changeBoatOwner(boatOwnerDTO);
        return new BoatOwnerDTO(editedBoatOwner);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @PostMapping(path = "/delete")
    public ResponseEntity<?> proccessBoatOwnerDeleting(@RequestBody DeleteDTO dto) {
        if(!boatOwnerService.findById(dto.id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        boatOwnerService.setWantedToDelete(dto.id);
        emailService.sendNotificationForDeletingToAdmin(dto.note, dto.id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/allBoats")
    public Set<BoatDTO> getAllBoats() {
        List<Boat> boats = boatOwnerService.findAllBoats();
        Set<BoatDTO> boatDTOS = new HashSet<>();
        for (Boat boat : boats) {
            BoatDTO dto = new BoatDTO(boat);
            dto.setAvgGrade(boatOwnerService.findAvgGradeForBoatId(boat.getId()));
            boatDTOS.add(dto);
        }

        return boatDTOS;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/findAvailableForDateRange")
    public Set<BoatDTO> findAvailableBoatsForDateRange(@RequestBody DateTimeRangeDTO dateRange) {
        Set<BoatDTO> boatDTOS = new HashSet<>();
        for (Boat boat : boatOwnerService.findAvailableBoatsForDateRange(dateRange)) {
            BoatDTO dto = new BoatDTO(boat);
            dto.setAvgGrade(boatOwnerService.findAvgGradeForBoatId(boat.getId()));
            boatDTOS.add(dto);
        }

        return boatDTOS;
    }

    @PreAuthorize("hasAnyRole('BOAT_OWNER', 'CUSTOMER')")
    @GetMapping(path = "/getAllFreeTermsForBoat/{id}")
    public Set<TermBoatDTO> getAllFreeTermsForBoat(@PathVariable Integer id) {
        Boat boat = boatOwnerService.findBoatById(id);
        List<TermBoat> terms = boatOwnerService.findAllFreeTermsForBoat(boat);
        Set<TermBoatDTO> termDtos = new HashSet<>();
        for (TermBoat term : terms)
            termDtos.add(new TermBoatDTO(term));

        return termDtos;
    }
}
