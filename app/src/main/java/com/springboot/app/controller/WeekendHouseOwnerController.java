package com.springboot.app.controller;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.model.dto.DeleteDTO;
import com.springboot.app.model.dto.WeekendHouseDTO;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;
import com.springboot.app.service.EmailService;
import com.springboot.app.service.WeekendHouseOwnerService;
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
@RequestMapping("/api/weekendhouseowners")
public class WeekendHouseOwnerController {
    private final WeekendHouseOwnerService weekendHouseOwnerService;
    private final EmailService emailService;
    @Autowired
    public WeekendHouseOwnerController(WeekendHouseOwnerService weekendHouseOwnerService, EmailService emailService) {
        this.weekendHouseOwnerService = weekendHouseOwnerService;
        this.emailService = emailService;
    }
    @PostMapping(path = "/create")
    public ResponseEntity<?> createWeekendHouseOwner(@RequestBody @Valid WeekendHouseOwnerDTO weekendHouseOwnerDTO, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        WeekendHouseOwner newWeekendHouseOwner = weekendHouseOwnerService.saveWeekendHouseOwner(new WeekendHouseOwner(weekendHouseOwnerDTO));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @GetMapping(path = "/{username}")
    public WeekendHouseOwnerDTO getWeekendHouseOwnerByUsername(@PathVariable String username) {
        WeekendHouseOwner weekendHouseOwner = weekendHouseOwnerService.findByUsername(username);
        return new WeekendHouseOwnerDTO(weekendHouseOwner);
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @PostMapping(path = "/edit")
    public WeekendHouseOwnerDTO editWeekendHouseOwner(@RequestBody WeekendHouseOwnerDTO weekendHouseOwnerDTO) {
        WeekendHouseOwner editedWeekendHouseOwner = weekendHouseOwnerService.changeWeekendHouseOwner(weekendHouseOwnerDTO);
        return new WeekendHouseOwnerDTO(editedWeekendHouseOwner);
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @PostMapping(path = "/createWeekendHouse")
    public ResponseEntity<?> createWeekendHouse(@RequestBody @Valid WeekendHouseDTO weekendHouseDTO, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        weekendHouseOwnerService.saveWeekendHouse(new WeekendHouse(weekendHouseDTO));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @PostMapping(path = "/delete")
    public ResponseEntity<?> proccessWeekendHouseOwnerDeleting(@RequestBody DeleteDTO dto) {
        if(!weekendHouseOwnerService.findById(dto.id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        weekendHouseOwnerService.setWantedToDelete(dto.id);
        emailService.sendNotificationForDeletingToAdmin(dto.note, dto.id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    @GetMapping(path = "/allWeekendHouses")
    public Set<WeekendHouseDTO> getAllWeekendHouses() {
        List<WeekendHouse> weekendHouses = weekendHouseOwnerService.findAllWeekendHouses();
        Set<WeekendHouseDTO> weekendHouseDTOs = new HashSet<>();
        for (WeekendHouse house : weekendHouses)
            weekendHouseDTOs.add(new WeekendHouseDTO(house));

        return weekendHouseDTOs;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/findAvailableForDateRange")
    public Set<WeekendHouseDTO> findAvailableHousesForDateRange(@RequestBody DateTimeRangeDTO dateRange) {
        Set<WeekendHouseDTO> weekendHouseDTOs = new HashSet<>();
        for (WeekendHouse house : weekendHouseOwnerService.findAvailableHousesForDateRange(dateRange))
            weekendHouseDTOs.add(new WeekendHouseDTO(house));

        return weekendHouseDTOs;
    }
}
