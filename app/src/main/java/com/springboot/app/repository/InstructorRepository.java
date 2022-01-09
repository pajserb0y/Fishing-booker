package com.springboot.app.repository;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    Instructor findByUsername(String username);
}
