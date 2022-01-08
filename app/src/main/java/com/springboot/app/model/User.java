package com.springboot.app.model;

import com.springboot.app.model.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import javax.persistence.Id;

@MappedSuperclass
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotEmpty(message = "Please fill out first name")
    protected String firstName;

    @NotEmpty(message = "Please fill out last name")
    protected String lastName;

    @Email(message = "Email is in wrong format")
    @NotBlank(message = "Please fill out email")
    protected String email;

    @NotEmpty(message = "Please fill out username")
    protected String username;

    @NotEmpty(message = "Please fill out password")
    protected String password;

    @NotEmpty(message = "Please fill out address")
    protected String address;

    @NotEmpty(message = "Please fill out city")
    protected String city;

    @NotEmpty(message = "Please fill out country")
    protected String country;

    @NotEmpty(message = "Please fill out phone")
    protected String phone;

    protected boolean isDeleted = false;
    protected boolean isActivated;

    public User(Integer id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, boolean isDeleted, boolean isActivated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.isDeleted = isDeleted;
        this.isActivated = isActivated;
    }
    public User(UserDTO userDTO)
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.id = userDTO.getId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.username = userDTO.getUsername();
        this.password = passwordEncoder.encode(userDTO.getPassword());
        this.address = userDTO.getAddress();
        this.city = userDTO.getCity();
        this.country = userDTO.getCountry();
        this.phone = userDTO.getPhone();
    }
    public User()
    {}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
        return isActivated;
    }
}
