package com.springboot.app.model.dto;

import com.springboot.app.model.Instructor;
import com.springboot.app.model.User;

public class InstructorDTO extends UserDTO {
    public InstructorDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone);
    }

    public InstructorDTO(Instructor instructor) {
        super(instructor);
    }
}