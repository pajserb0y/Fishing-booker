package com.springboot.app.repository;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.WeekendHouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekendHouseOwnerRepository extends JpaRepository<WeekendHouseOwner,Integer> {
    WeekendHouseOwner findByUsername(String username);
}
