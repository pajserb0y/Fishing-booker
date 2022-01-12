package com.springboot.app.controller;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.Customer;
import com.springboot.app.model.dto.BoatOwnerDTO;
import com.springboot.app.model.dto.CustomerDTO;
import com.springboot.app.model.dto.DeleteDTO;
import com.springboot.app.service.BoatOwnerService;
import com.springboot.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
