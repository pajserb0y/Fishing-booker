package com.springboot.app.service;

import com.springboot.app.model.FishingLessonReservation;

import java.util.List;


public interface FishingLessonReservationService {

    FishingLessonReservation reserve(FishingLessonReservation fishingLessonReservation);

    List<FishingLessonReservation> getFutureResForCustomer(String customerUsername);

    List<FishingLessonReservation> getFutureResForInstructor(String instructorUsername);
}
