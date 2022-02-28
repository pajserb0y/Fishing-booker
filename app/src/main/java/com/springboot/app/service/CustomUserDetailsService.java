package com.springboot.app.service;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.Customer;
import com.springboot.app.model.Instructor;
import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.repository.BoatOwnerRepository;
import com.springboot.app.repository.CustomerRepository;
import com.springboot.app.repository.InstructorRepository;
import com.springboot.app.repository.WeekendHouseOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// Ovaj servis je namerno izdvojen kao poseban u ovom primeru.
// U opstem slucaju UserServiceImpl klasa bi mogla da implementira UserDetailService interfejs.
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository userRepository;
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private WeekendHouseOwnerRepository weekendHouseOwnerRepository;


    // Funkcija koja na osnovu username-a iz baze vraca objekat User-a
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = userRepository.findByUsername(username);
        BoatOwner boatOwner = boatOwnerRepository.findByUsername(username);
        Instructor instructor = instructorRepository.findByUsername(username);
        WeekendHouseOwner weekendHouseOwner = weekendHouseOwnerRepository.findByUsername(username);

        if (customer==null && boatOwner==null && instructor==null && weekendHouseOwner==null)
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        else if (customer != null)
            return customer;
        else if (boatOwner != null)
            return boatOwner;
        else if (instructor != null)
            return instructor;
        else
            return weekendHouseOwner;
    }

}
