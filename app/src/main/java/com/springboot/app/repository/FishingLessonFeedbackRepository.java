package com.springboot.app.repository;

import com.springboot.app.model.BoatFeedback;
import com.springboot.app.model.FishingLessonFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingLessonFeedbackRepository extends JpaRepository<FishingLessonFeedback, Integer> {

    @Query(value = "SELECT avg(f.gradeFishingLesson) FROM FishingLessonFeedback f WHERE f.fishingLessonReservation.fishingLesson.id = :id AND f.isApproved = true")
    Integer findAverageGradeFishingLessonByFishingLessonId(Integer id);
}
