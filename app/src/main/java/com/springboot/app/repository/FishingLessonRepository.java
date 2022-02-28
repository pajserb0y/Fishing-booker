package com.springboot.app.repository;

import com.springboot.app.model.FishingLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface FishingLessonRepository extends JpaRepository<FishingLesson, Integer> {

    List<FishingLesson> findAllByIdNotIn(Collection<Integer> fishingLessonIds);

    //@Query("SELECT fl FROM FishingLessons fl WHERE fl.instructor.id = :id")
    List<FishingLesson> findAllByInstructorId(@Param("id") Integer instructorId);
}
