package com.springboot.app.repository;

import com.springboot.app.model.FishingLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingLessonRepository extends JpaRepository<FishingLesson, Integer> {

}
