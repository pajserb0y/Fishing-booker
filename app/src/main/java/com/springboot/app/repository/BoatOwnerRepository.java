package com.springboot.app.repository;

import com.springboot.app.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner,Integer> {
    BoatOwner findByUsername(String username);
}
