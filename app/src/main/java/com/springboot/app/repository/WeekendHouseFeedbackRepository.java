package com.springboot.app.repository;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeekendHouseFeedbackRepository extends JpaRepository<WeekendHouseFeedback, Integer> {

    @Query(value = "SELECT avg(f.gradeHouse) FROM WeekendHouseFeedback f WHERE f.weekendHouseReservation.weekendHouse.id = :id AND f.isApproved = true")
    Integer findAverageGradeHouseByWeekendHouseId(@Param("id") Integer id);
}
