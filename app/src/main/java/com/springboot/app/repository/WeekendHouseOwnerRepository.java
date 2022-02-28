package com.springboot.app.repository;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.WeekendHouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface WeekendHouseOwnerRepository extends JpaRepository<WeekendHouseOwner,Integer> {
    WeekendHouseOwner findByUsername(String username);

    @Query("SELECT w.username FROM WeekendHouseOwner w")
    Collection<String> findAllUsernames();
}
