package com.springboot.app.repository;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WeekendHouseReservationRepository extends JpaRepository<WeekendHouseReservation,Integer> {

    @Query("SELECT r.weekendHouse.id FROM WeekendHouseReservation r WHERE r.customer IS NOT null AND r.isCancelled IS false AND r.endSpecialOffer IS null " +
                    "AND ((cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                        "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                        "OR (r.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
                        "OR (r.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)))")
    List<Integer> findAllForDateRange(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT a FROM WeekendHouseReservation a WHERE a.customer.username = :username AND a.startDateTime > cast(NOW() as timestamp)")
    List<WeekendHouseReservation> findAllFutureReservationsByCustomerUsername(@Param("username") String username);

    @Query("SELECT a FROM WeekendHouseReservation a WHERE a.customer.username = :username AND a.startDateTime <= cast(NOW() as timestamp)")
    List<WeekendHouseReservation> findAllPastReservationsByCustomerUsername(@Param("username") String username);

    List<WeekendHouseReservation> findByWeekendHouse(WeekendHouse weekendHouse);

    @Query("SELECT res.id FROM WeekendHouseReservation res WHERE res.weekendHouse.id = :id AND res.startDateTime > cast(NOW() as timestamp)")
    List<Integer> existsFutureReservation(@Param("id") Integer houseId);

    @Query("SELECT a FROM WeekendHouseReservation a WHERE a.customer IS null AND a.isCancelled IS false " +
                                                        "AND a.startSpecialOffer <= cast(NOW() as timestamp) AND a.endSpecialOffer >= cast(NOW() as timestamp)")
    List<WeekendHouseReservation> getCurrentSpecialOffers();

    @Modifying
    @Query("UPDATE WeekendHouseReservation r SET r.isCancelled = true WHERE r.customer IS null AND r.isCancelled IS false AND r.weekendHouse.id = :houseId " +
                                                                        "AND ((cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                                                                            "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                                                                            "OR (r.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
                                                                            "OR (r.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)))")
    void cancellAllActionsForThisDateRangeForThisWeekendHouse(@Param("start") Date start, @Param("end") Date end, @Param("houseId") Integer weekendHouseId);
}
