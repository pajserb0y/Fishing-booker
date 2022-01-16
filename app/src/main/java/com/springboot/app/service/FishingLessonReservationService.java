package com.springboot.app.service;

import com.springboot.app.model.*;

import java.util.List;
import java.util.Optional;


public interface FishingLessonReservationService {

    FishingLessonReservation reserve(FishingLessonReservation fishingLessonReservation);

    List<FishingLessonReservation> getFutureResForInstructor(Instructor instructorUsername);

    List<FishingLessonReservation> getFutureForCustomerUsername(String username);

    List<FishingLessonReservation> getPastForCustomerUsername(String username);

    void cancel(Integer id);

    Optional<FishingLessonReservation> findById(Integer fishingLessonReservationId);

    void sendFeedback(FishingLessonFeedback fishingLessonFeedback);

    void sendComplaint(FishingLessonComplaint fishingLessonComplaint);
}
