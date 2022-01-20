package com.springboot.app.repository;

import com.springboot.app.model.BoatReservation;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WeekendHouseReservationRepository extends JpaRepository<WeekendHouseReservation,Integer> {

    @Query("SELECT r.weekendHouse.id FROM WeekendHouseReservation r WHERE r.isCancelled IS false AND (cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                        "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
            "OR (r.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
                        "OR (r.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp))")
    List<Integer> findAllForDateRange(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT a FROM WeekendHouseReservation a WHERE a.customer.username = :username AND a.startDateTime > cast(NOW() as timestamp)")
    List<WeekendHouseReservation> findAllFutureReservationsByCustomerUsername(@Param("username") String username);

    @Query("SELECT a FROM WeekendHouseReservation a WHERE a.customer.username = :username AND a.startDateTime <= cast(NOW() as timestamp)")
    List<WeekendHouseReservation> findAllPastReservationsByCustomerUsername(@Param("username") String username);

    List<WeekendHouseReservation> findByWeekendHouse(WeekendHouse weekendHouse);

    @Query("SELECT res.id FROM WeekendHouseReservation res WHERE res.weekendHouse.id = :id AND res.startDateTime > cast(NOW() as timestamp)")
    List<Integer> existsFutureReservation(@Param("id") Integer houseId);

    @Query("SELECT a FROM WeekendHouseReservation a WHERE a.customer = null AND a.endSpecialOffer != null AND a.startSpecialOffer <= cast(NOW() as timestamp)" +
                                                        "AND a.endSpecialOffer >= cast(NOW() as timestamp)")
    List<WeekendHouseReservation> getCurrentSpecialOffers();

    @Query("SELECT res FROM WeekendHouseReservation res WHERE res.weekendHouse.weekendHouseOwner.id = :id")
    List<WeekendHouseReservation> findAllReservationsForInstructor(@Param("id") Integer weekendHouseOwnerId);
}
