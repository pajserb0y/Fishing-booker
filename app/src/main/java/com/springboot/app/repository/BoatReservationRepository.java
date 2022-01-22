package com.springboot.app.repository;

import com.springboot.app.model.Boat;
import com.springboot.app.model.BoatReservation;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BoatReservationRepository extends JpaRepository<BoatReservation,Integer> {

    @Query("SELECT r.boat.id FROM BoatReservation r WHERE r.customer IS NOT null AND r.isCancelled IS false " +
            "AND ((cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                "OR (r.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
                "OR (r.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)))")
    List<Integer> findAllForDateRange(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT a FROM BoatReservation a WHERE a.customer.username = :username AND a.startDateTime > cast(NOW() as timestamp)")
    List<BoatReservation> findAllFutureReservationsByCustomerUsername(@Param("username") String username);

    @Query("SELECT a FROM BoatReservation a WHERE a.customer.username = :username AND a.startDateTime <= cast(NOW() as timestamp)")
    List<BoatReservation> findAllPastReservationsByCustomerUsername(@Param("username") String username);

    @Query("SELECT res.id FROM BoatReservation res WHERE res.boat.id = :id AND res.startDateTime > cast(NOW() as timestamp)")
    List<Integer> existsFutureReservation(@Param("id") Integer boatId);

    @Query("SELECT a FROM BoatReservation a WHERE a.customer = null AND a.isCancelled IS false AND a.endSpecialOffer != null " +
            "AND a.startSpecialOffer <= cast(NOW() as timestamp)" +
            "AND a.endSpecialOffer >= cast(NOW() as timestamp)")
    List<BoatReservation> getCurrentSpecialOffers();

    @Modifying
    @Query("UPDATE BoatReservation r SET r.isCancelled = true WHERE r.customer IS null AND r.isCancelled IS false AND r.boat.id = :boatId " +
                                                        "AND ((cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                                                        "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
                                                        "OR (r.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
                                                        "OR (r.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)))")
    void cancellAllActionsForThisDateRangeForThisBoat(@Param("start") Date start, @Param("end") Date end, @Param("boatId") Integer boatId);

    List<BoatReservation> findByBoat(Boat boat);
    @Query("SELECT res FROM BoatReservation res WHERE res.boat.boatOwner.id = :id")
    List<BoatReservation> findAllReservationsForBoatOwner(@Param("id") Integer boatOwnerId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from BoatReservation p where p.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Optional<BoatReservation> findOneById(@Param("id") Integer id);

//
//    List<WeekendHouseReservation> findByWeekendHouse(WeekendHouse weekendHouse);
}
