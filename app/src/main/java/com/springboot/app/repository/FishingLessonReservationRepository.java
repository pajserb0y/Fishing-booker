package com.springboot.app.repository;

import com.springboot.app.model.FishingLessonReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FishingLessonReservationRepository extends JpaRepository<FishingLessonReservation, Integer> {

    List<FishingLessonReservation> findAllByCustomerUsername(String customerUsername);

    List<FishingLessonReservation> findAllByInstructorUsername(String instructorUsername);
}
