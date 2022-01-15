package com.springboot.app.model;

import com.springboot.app.model.dto.BoatOwnerDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
public class BoatOwner extends SystemUser implements UserDetails {
    private String motive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "boatOwner")
    private Set<Boat> boats = new HashSet<>();


    public BoatOwner() {
        super();
    }

    public BoatOwner(Integer id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, boolean isDeleted, boolean isActivated, Role roles, String motive) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone, isDeleted, isActivated, roles);
        this.motive = motive;
        this.role = roles;
    }

    public BoatOwner(BoatOwnerDTO boatOwnerDTO) {
        super(boatOwnerDTO);
        this.motive = boatOwnerDTO.getMotive();
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }
}
