package com.springboot.app.repository;

import com.springboot.app.model.BoatFeedback;
import com.springboot.app.model.WeekendHouseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoatFeedbackRepository extends JpaRepository<BoatFeedback, Integer> {

    @Query(value = "SELECT avg(f.gradeBoat) FROM BoatFeedback f WHERE f.boatReservation.boat.id = :id AND f.isApproved = true")
    Integer findAverageGradeBoatByBoatId(Integer id);


}
