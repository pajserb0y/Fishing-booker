package com.springboot.app.model;

import com.springboot.app.model.dto.InstructorDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Instructor extends SystemUser implements UserDetails {
    private String motive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public Instructor(Integer id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, boolean isDeleted, boolean isActivated, Role roles, String motive) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone, isDeleted, isActivated, roles);
        this.motive = motive;
        this.role = roles;
    }

    public void edit(InstructorDTO dto) {

        this.phone = dto.getPhone();
        this.country = dto.getCountry();
        this.city = dto.getCity();
        this.address = dto.getAddress();
        this.lastName = dto.getLastName();
        this.firstName = dto.getFirstName();
        this.motive = dto.getMotive();
    }

    public Instructor() {
        super();
    }

    public Instructor(InstructorDTO instructorDTO) {
        super(instructorDTO);
        this.motive = instructorDTO.getMotive();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList( this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActivated;
    }
}
