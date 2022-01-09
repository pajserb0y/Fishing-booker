package com.springboot.app.model.dto;

import com.springboot.app.model.Instructor;

public class InstructorDTO extends UserDTO {
    private String motive;
    public InstructorDTO(int id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone);
    }

    public InstructorDTO(Instructor instructor) {
        super(instructor);
        this.motive = instructor.getMotive();
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}