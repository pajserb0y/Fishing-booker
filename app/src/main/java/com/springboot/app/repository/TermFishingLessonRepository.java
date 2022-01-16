package com.springboot.app.repository;

import com.springboot.app.model.FishingLesson;
import com.springboot.app.model.TermBoat;
import com.springboot.app.model.TermFishingLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface TermFishingLessonRepository extends JpaRepository<TermFishingLesson, Integer> {

    @Query("SELECT r.fishingLesson.id FROM TermFishingLesson r WHERE (cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
            "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
            "OR (r.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
            "OR (r.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp))")
    List<Integer> findAllFishingLessonIdsThatWorkForPeriod(@Param("start") Date start, @Param("end") Date end);

    List<TermFishingLesson> findByFishingLesson(FishingLesson fishingLesson);
}
