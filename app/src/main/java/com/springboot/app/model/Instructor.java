package com.springboot.app.model;

import com.springboot.app.model.dto.InstructorDTO;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Instructor extends User{

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "boat_owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id"))
    private List<Role> roles;
    private String motive;

    public Instructor(Integer id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, boolean isDeleted, boolean isActivated, List<Role> roles, String motive) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone, isDeleted, isActivated);
        this.roles = roles;
        this.motive = motive;
    }

    public Instructor(InstructorDTO instructorDTO, List<Role> roles, String motive) {
        super(instructorDTO);
        this.roles = roles;
        this.motive = motive;
    }
}
