package com.springboot.app.repository;

import com.springboot.app.model.Customer;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FishingLessonReservationRepository extends JpaRepository<FishingLessonReservation, Integer> {

    List<FishingLessonReservation> findAllByCustomer(Customer customer);

    List<FishingLessonReservation> findAllByFishingLessonInstructor(Instructor instructor);

    @Query("SELECT res FROM FishingLessonReservation res WHERE res.customer.username = :username AND res.startDateTime > cast(NOW() as timestamp)")
    List<FishingLessonReservation> findAllFutureReservationsByCustomerUsername(@Param("username") String username);

    @Query("SELECT res FROM FishingLessonReservation res WHERE res.customer.username = :username AND res.startDateTime <= cast(NOW() as timestamp)")
    List<FishingLessonReservation> findAllPastReservationsByCustomerUsername(@Param("username") String username);

    @Query("SELECT res.fishingLesson.id FROM FishingLessonReservation res WHERE res.isCancelled IS false AND (cast(:start as timestamp) BETWEEN res.startDateTime AND res.endDateTime) " +
            "OR (cast(:end as timestamp) BETWEEN res.startDateTime AND res.endDateTime) " +
            "OR (res.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
            "OR (res.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp))")
    List<Integer> findAllForDateRange(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT res.id FROM FishingLessonReservation res WHERE res.fishingLesson.id = :id AND res.startDateTime > cast(NOW() as timestamp)")
    List<Integer> existsFutureReservation(@Param("id") Integer fishingLessonId);

    @Query("SELECT res FROM FishingLessonReservation res WHERE res.fishingLesson.instructor.id = :id AND res.startDateTime > cast(NOW() as timestamp)")
    List<FishingLessonReservation> findOnlyFutureReservationsForInstructor(@Param("id") Integer instructorId);

    @Query("SELECT res FROM FishingLessonReservation res WHERE res.fishingLesson.instructor.id = :id")
    List<FishingLessonReservation> findAllReservationsForInstructor(@Param("id") Integer instructorId);

    @Query("SELECT res FROM FishingLessonReservation res WHERE res.fishingLesson.id = :id")
    List<FishingLessonReservation> findAllByFishingLessonId(@Param("id") Integer fishingLessonId);

    @Query("SELECT a FROM FishingLessonReservation a WHERE a.customer = null AND a.endSpecialOffer != null AND a.startSpecialOffer <= cast(NOW() as timestamp)" +
            "AND a.endSpecialOffer >= cast(NOW() as timestamp)")
    List<FishingLessonReservation> getCurrentSpecialOffers();
}
