package com.springboot.app.repository;

import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    Instructor findByUsername(String username);

    @Query("SELECT c.username FROM Instructor c")
    Collection<String> findAllUsernames();
}
