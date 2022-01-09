package com.springboot.app.controller;

import com.springboot.app.model.Instructor;
import com.springboot.app.model.dto.InstructorDTO;
import com.springboot.app.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
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
}
