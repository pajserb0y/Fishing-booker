package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.Instructor;

import java.util.List;


public interface FishingLessonReservationService {

    FishingLessonReservation reserve(FishingLessonReservation fishingLessonReservation);

    List<FishingLessonReservation> getFutureResForInstructor(Instructor instructorUsername);

    List<FishingLessonReservation> getFutureForCustomerUsername(String username);

    List<FishingLessonReservation> getPastForCustomerUsername(String username);

    void cancel(Integer id);
}
