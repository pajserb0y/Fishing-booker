package com.springboot.app.controller;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.WeekendHouseDTO;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;
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

    @Autowired
    public WeekendHouseOwnerController(WeekendHouseOwnerService weekendHouseOwnerService) {
        this.weekendHouseOwnerService = weekendHouseOwnerService;
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
    public WeekendHouseOwnerDTO editCustomer(@RequestBody WeekendHouseOwnerDTO weekendHouseOwnerDTO) {
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

    @GetMapping(path = "/allWeekendHouses")
    public Set<WeekendHouseDTO> getAllWeekendHouses() {
        List<WeekendHouse> weekendHouses = weekendHouseOwnerService.findAllWeekendHouses();
        Set<WeekendHouseDTO> weekendHouseDTOs = new HashSet<>();
        for (WeekendHouse house : weekendHouses)
            weekendHouseDTOs.add(new WeekendHouseDTO(house));

        return weekendHouseDTOs;
    }
}
