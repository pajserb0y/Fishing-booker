package com.springboot.app.repository;

import com.springboot.app.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner,Integer> {
    BoatOwner findByUsername(String username);

    @Query("SELECT b.username FROM BoatOwner b")
    Collection<String> findAllUsernames();
}
