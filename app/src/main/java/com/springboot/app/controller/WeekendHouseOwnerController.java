package com.springboot.app.controller;

import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;
import com.springboot.app.service.WeekendHouseOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/weekendhouseowners")
public class WeekendHouseOwnerController {
    private final WeekendHouseOwnerService weekendHouseOwnerService;

    @Autowired
    public WeekendHouseOwnerController(WeekendHouseOwnerService weekendHouseOwnerService) {
        this.weekendHouseOwnerService = weekendHouseOwnerService;
    }
    @PostMapping
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
}
