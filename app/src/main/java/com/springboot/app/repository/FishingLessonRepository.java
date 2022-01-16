package com.springboot.app.repository;

import com.springboot.app.model.FishingLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FishingLessonRepository extends JpaRepository<FishingLesson, Integer> {

    List<FishingLesson> findAllByIdNotIn(Collection<Integer> fishingLessonIds);
}
