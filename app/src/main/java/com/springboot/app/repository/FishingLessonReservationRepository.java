package com.springboot.app.repository;

import com.springboot.app.model.Customer;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FishingLessonReservationRepository extends JpaRepository<FishingLessonReservation, Integer> {

    List<FishingLessonReservation> findAllByCustomer(Customer customer);

    List<FishingLessonReservation> findAllByFishingLessonInstructor(Instructor instructor);

    @Query("SELECT a FROM FishingLessonReservation a WHERE a.customer.username = :username AND a.startDateTime > cast(NOW() as timestamp)")
    List<FishingLessonReservation> findAllFutureReservationsByCustomerUsername(@Param("username") String username);

    @Query("SELECT a FROM FishingLessonReservation a WHERE a.customer.username = :username AND a.startDateTime <= cast(NOW() as timestamp)")
    List<FishingLessonReservation> findAllPastReservationsByCustomerUsername(@Param("username") String username);
}
