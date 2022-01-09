package com.springboot.app.service;

import com.springboot.app.model.Instructor;
import com.springboot.app.model.dto.InstructorDTO;

public interface InstructorService {
    Instructor saveInstructor(Instructor instructor);

    Instructor findByUsername(String username);

    Instructor changeInstructor(InstructorDTO instructorDTO);
}
