package com.springboot.app.model;

import com.springboot.app.model.dto.CustomerDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Customer extends SystemUser implements UserDetails {
    private String hashCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public Customer(Integer id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, boolean isDeleted, boolean isActivated, Role roles, String hashCode) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone, isDeleted, isActivated, roles);
        this.hashCode = hashCode;
        this.role = roles;
    }

    public Customer() { }

    public Customer(CustomerDTO customerDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.id = customerDto.getId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
        this.username = customerDto.getUsername();
        this.password = passwordEncoder.encode(customerDto.getPassword());
        this.address = customerDto.getAddress();
        this.city = customerDto.getCity();
        this.country = customerDto.getCountry();
        this.phone = customerDto.getPhone();
        this.hashCode = generateHashCode(this.password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static String generateHashCode(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // Change this to UTF-16 if needed
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        String hex = String.format("%064x", new BigInteger(1, digest));
        return hex;
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


