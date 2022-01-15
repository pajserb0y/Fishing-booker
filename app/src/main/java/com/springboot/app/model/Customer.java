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
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends SystemUser implements UserDetails {
    private String hashCode;
    private Integer penals;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WeekendHouseReservation> weekendHouseReservations = new HashSet<WeekendHouseReservation>();

    public Customer(Integer id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, boolean isDeleted, boolean isActivated, Role roles, String hashCode, Integer penals) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone, isDeleted, isActivated, roles);
        this.hashCode = hashCode;
        this.role = roles;
        this.penals = penals;
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
        this.penals = customerDto.getPenals();
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

    public Integer getPenals() {
        return penals;
    }

    public void setPenals(Integer penals) {
        this.penals = penals;
    }

    public Set<WeekendHouseReservation> getWeekendHouseReservations() {
        return weekendHouseReservations;
    }

    public void setWeekendHouseReservations(Set<WeekendHouseReservation> weekendHouseReservations) {
        this.weekendHouseReservations = weekendHouseReservations;
    }

    public static Customer DtoToCustomerWithoutHashingPassword(CustomerDTO customerDto ) {
        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setCountry(customerDto.getCountry());
        customer.setPhone(customerDto.getPhone());
        customer.setHashCode(customerDto.getAddress());

        return customer;
    }
}


