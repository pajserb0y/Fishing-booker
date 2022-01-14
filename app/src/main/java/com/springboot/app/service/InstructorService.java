package com.springboot.app.service;

import com.springboot.app.model.FishingLesson;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.Instructor;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.model.dto.FishingLessonDTO;
import com.springboot.app.model.dto.InstructorDTO;

import java.util.List;

public interface InstructorService {
    Instructor saveInstructor(Instructor instructor);

    Instructor findByUsername(String username);

    Instructor changeInstructor(InstructorDTO instructorDTO);

    void addFishingLessonForInstructor(FishingLessonDTO fishingLessonDTO, Integer id);

    FishingLesson changeFishingLesson(FishingLessonDTO fishingLessonDTO);

    boolean removeFishingLesson(Integer id);

    List<FishingLesson> findAllFishingLessons();

    List<FishingLesson> findAllAvailableFishingLessons(DateTimeRangeDTO dateRange);

    List<FishingLesson> findAllFishingLessonsForInstructor(Integer instructorId);

    List<FishingLessonReservation> findAllReservationsForInstructor(Integer instructorId, boolean futureOnly);

}
