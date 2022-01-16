package com.springboot.app.controller;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.*;
import com.springboot.app.service.EmailService;
import com.springboot.app.service.InstructorService;
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
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;
    private final EmailService emailService;

    @Autowired
    public InstructorController(InstructorService instructorService, EmailService emailService) {
        this.instructorService = instructorService;
        this.emailService = emailService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createInstructor(@RequestBody @Valid InstructorDTO instructorDTO, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Instructor newInstructor = instructorService.saveInstructor(new Instructor(instructorDTO));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping(path = "/{username}")
    public InstructorDTO getInstructorByUsername(@PathVariable String username) {
        Instructor instructor = instructorService.findByUsername(username);
        return new InstructorDTO(instructor);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping(path = "/edit")
    public InstructorDTO editInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor editedInstructor = instructorService.changeInstructor(instructorDTO);
        return new InstructorDTO(editedInstructor);
    }

    @GetMapping(path = "/allFishingLessons")
    public Set<FishingLessonDTO> getAllFishingLessons() {
        List<FishingLesson> fishingLessons = instructorService.findAllFishingLessons();
        Set<FishingLessonDTO> fishingLessonDTOS = new HashSet<>();
        for (FishingLesson fishingLesson : fishingLessons) {
            FishingLessonDTO dto = new FishingLessonDTO(fishingLesson);
            dto.setAvgGrade(instructorService.findAvgGradeForFishingLessonId(fishingLesson.getId()));
            fishingLessonDTOS.add(dto);
        }

        return fishingLessonDTOS;
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping(path = "/allFishingLessonsForInstructor/{username}")
    public Set<FishingLessonDTO> allFishingLessonsForInstructor(@PathVariable String username) {
        Integer instructorId = instructorService.findByUsername(username).getId();
        List<FishingLesson> fishingLessons = instructorService.findAllFishingLessonsForInstructor(instructorId);
        Set<FishingLessonDTO> fishingLessonDTOS = new HashSet<>();
        for (FishingLesson fishingLesson : fishingLessons) {
            FishingLessonDTO dto = new FishingLessonDTO(fishingLesson);
            dto.setAvgGrade(instructorService.findAvgGradeForFishingLessonId(fishingLesson.getId()));
            fishingLessonDTOS.add(dto);
        }

        return fishingLessonDTOS;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(path = "/findAvailableForDateRange")
    public Set<FishingLessonDTO> findAvailableLessonsForDateRange(@RequestBody DateTimeRangeDTO dateRange) {
        Set<FishingLessonDTO> fishingLessonDTOS = new HashSet<>();
        for (FishingLesson fishingLesson : instructorService.findAllAvailableFishingLessons(dateRange)) {
            FishingLessonDTO dto = new FishingLessonDTO(fishingLesson);
            dto.setAvgGrade(instructorService.findAvgGradeForFishingLessonId(fishingLesson.getId()));
            fishingLessonDTOS.add(dto);
        }

        return fishingLessonDTOS;
    }

    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'CUSTOMER')")
    @GetMapping(path = "/getAllFreeTermsForFishingLesson/{id}")
    public Set<TermFishingLessonDTO> getAllFreeTermsForFishingLesson(@PathVariable Integer id) {
        FishingLesson fishingLesson = instructorService.findFishingLessonById(id);
        List<TermFishingLesson> terms = instructorService.findFreeTermsForFishingLesson(fishingLesson);
        Set<TermFishingLessonDTO> termDtos = new HashSet<>();
        for (TermFishingLesson term : terms)
            termDtos.add(new TermFishingLessonDTO(term));

        return termDtos;
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping(path = "/getAllReservationsForFishingLesson/{id}")
    public Set<FishingLessonReservationDTO> getAllReservationsForFishingLesson(@PathVariable Integer fishingLessonId) {
        List<FishingLessonReservation> fishingLessonReservations = instructorService.findAllReservationsForFishingLesson(fishingLessonId);
        Set<FishingLessonReservationDTO> fishingLessonReservationDTOs = new HashSet<>();
        for (FishingLessonReservation reservation : fishingLessonReservations)
            fishingLessonReservationDTOs.add(new FishingLessonReservationDTO(reservation));
        return fishingLessonReservationDTOs;
    }
}
