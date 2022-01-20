package com.springboot.app.controller;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.dto.*;
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
    @PostMapping(path = "/editWeekendHouse")
    public WeekendHouseDTO editWeekendHouse(@RequestBody WeekendHouseDTO weekendHouseDTO) {
        WeekendHouse editedWeekendHouse = weekendHouseOwnerService.changeWeekendHouse(weekendHouseDTO);
        return new WeekendHouseDTO(editedWeekendHouse);
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
    @PostMapping(path = "/addFreeTerm")
    public Set<TermDto> addFreeTerm(@RequestBody TermDto termDto){
        Term term = new Term(termDto);
        if (weekendHouseOwnerService.addFreeTerm(term).equals(null))
            return null;

        return this.getAllFreeTermsForWeekendHouse(term.getWeekendHouse().getId());
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @PostMapping(path = "/delete")
    public ResponseEntity<?> proccessWeekendHouseOwnerDeleting(@RequestBody DeleteDTO dto) {
        if (!weekendHouseOwnerService.findById(dto.id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        weekendHouseOwnerService.setWantedToDelete(dto.id);
        emailService.sendNotificationForDeletingToAdmin(dto.note, dto.id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @PostMapping(path = "/removeWeekendHouse/{id}")
    public ResponseEntity<?> removeWeekendHouse(@PathVariable Integer id) {
        if (weekendHouseOwnerService.removeWeekendHouse(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/allWeekendHouses")
    public Set<WeekendHouseWithAvgGradeDTO> getAllWeekendHouses() {
        List<WeekendHouse> weekendHouses = weekendHouseOwnerService.findAllWeekendHouses();
        Set<WeekendHouseWithAvgGradeDTO> weekendHouseWithAvgGradeDTOs = new HashSet<>();
        for (WeekendHouse house : weekendHouses) {
            WeekendHouseWithAvgGradeDTO dto = new WeekendHouseWithAvgGradeDTO();
            dto.setWeekendHouse(new WeekendHouseDTO(house));
            dto.setAvgGrade(weekendHouseOwnerService.findAvgGradeForHouseId(house.getId()));
            weekendHouseWithAvgGradeDTOs.add(dto);
        }

        return weekendHouseWithAvgGradeDTOs;
    }

    @PreAuthorize("hasRole('WEEKEND_HOUSE_OWNER')")
    @GetMapping(path = "/allWeekendHousesForOwner/{username}")
    public Set<WeekendHouseWithAvgGradeDTO> allWeekendHousesForOwner(@PathVariable String username) {
        WeekendHouseOwner weekendHouseOwner = weekendHouseOwnerService.findByUsername(username);
        List<WeekendHouse> weekendHouses = weekendHouseOwnerService.findallWeekendHousesForOwner(weekendHouseOwner);
        Set<WeekendHouseWithAvgGradeDTO> weekendHouseWithAvgGradeDTOs = new HashSet<>();
        for (WeekendHouse house : weekendHouses) {
            WeekendHouseWithAvgGradeDTO dto = new WeekendHouseWithAvgGradeDTO();
            dto.setWeekendHouse(new WeekendHouseDTO(house));
            dto.setAvgGrade(weekendHouseOwnerService.findAvgGradeForHouseId(house.getId()));
            weekendHouseWithAvgGradeDTOs.add(dto);
        }

        return weekendHouseWithAvgGradeDTOs;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/findAvailableForDateRange")
    public Set<WeekendHouseWithAvgGradeDTO> findAvailableHousesForDateRange(@RequestBody DateTimeRangeDTO dateRange) {
        Set<WeekendHouseWithAvgGradeDTO> weekendHouseWithAvgGradeDTOs = new HashSet<>();
        for (WeekendHouse house : weekendHouseOwnerService.findAvailableHousesForDateRange(dateRange)) {
            WeekendHouseWithAvgGradeDTO dto = new WeekendHouseWithAvgGradeDTO();
            dto.setWeekendHouse(new WeekendHouseDTO(house));
            dto.setAvgGrade(weekendHouseOwnerService.findAvgGradeForHouseId(house.getId()));
            weekendHouseWithAvgGradeDTOs.add(dto);
        }

        return weekendHouseWithAvgGradeDTOs;
    }

    @PreAuthorize("hasAnyRole('WEEKEND_HOUSE_OWNER', 'CUSTOMER')")
    @GetMapping(path = "/getAllFreeTermsForWeekendHouse/{id}")
    public Set<TermDto> getAllFreeTermsForWeekendHouse(@PathVariable Integer id) {
        WeekendHouse weekendHouse = weekendHouseOwnerService.findWeekendHouseById(id);
        List<Term> terms = weekendHouseOwnerService.findAllFreeTermsForWeekendHouse(weekendHouse);
        Set<TermDto> termDtos = new HashSet<>();
        for (Term term : terms)
            termDtos.add(new TermDto(term));

        return termDtos;
    }
}
