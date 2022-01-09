package com.springboot.app.controller;

import javax.servlet.http.HttpServletResponse;

import com.springboot.app.model.Customer;
import com.springboot.app.model.dto.CustomerDTO;
import com.springboot.app.model.dto.UserCredentials;
import com.springboot.app.security.tokenUtils.JwtTokenUtils;
import com.springboot.app.service.CustomerService;
import com.springboot.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final JwtTokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;

    @Autowired
    public AuthenticationController(CustomerService customerService, AuthenticationManager authenticationManager, JwtTokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
    }

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody UserCredentials authenticationRequest, HttpServletResponse response) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        Customer customer = (Customer) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(customer.getUsername(), customer.getRole());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return jwt;
    }

    @GetMapping(path = "/getAllUsernames")
    public Set<String> getCustomerByUsername() {
        Set<String> usernameList = new HashSet<String>();
        usernameList.addAll(customerService.findAllUsernames());

        return usernameList;
    }

}
