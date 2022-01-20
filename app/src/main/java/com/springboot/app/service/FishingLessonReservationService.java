package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.FishingLessonReservationDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface FishingLessonReservationService {

    FishingLessonReservation reserve(FishingLessonReservation fishingLessonReservation);

    List<FishingLessonReservation> getFutureResForInstructor(Instructor instructorUsername);

    List<FishingLessonReservation> getFutureForCustomerUsername(String username);

    List<FishingLessonReservation> getPastForCustomerUsername(String username);

    void cancel(Integer id);

    Optional<FishingLessonReservation> findById(Integer fishingLessonReservationId);

    void sendFeedback(FishingLessonFeedback fishingLessonFeedback);

    void sendComplaint(FishingLessonComplaint fishingLessonComplaint);

    List<FishingLessonReservation> getCurrentSpecialOffers();

    List<FishingLessonReservation> findAllReservationsForFishingLesson(FishingLesson fishingLesson);
    
    Set<FishingLessonReservationDTO> getAllReservationsForInstructor(String username);
}
