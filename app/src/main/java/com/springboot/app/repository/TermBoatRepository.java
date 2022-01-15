package com.springboot.app.repository;

import com.springboot.app.model.Boat;
import com.springboot.app.model.Term;
import com.springboot.app.model.TermBoat;
import com.springboot.app.model.WeekendHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TermBoatRepository extends JpaRepository<TermBoat, Integer> {
//    List<Term> findByWeekendHouse(WeekendHouse weekendHouse);

    @Query("SELECT r.boat.id FROM TermBoat r WHERE (cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
            "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
            "OR (r.startDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp)) " +
            "OR (r.endDateTime BETWEEN cast(:start as timestamp) AND cast(:end as timestamp))")
//     @Query("SELECT r.weekendHouse.id FROM Term r WHERE (cast(:start as timestamp) NOT BETWEEN r.startDateTime AND r.endDateTime) " +
//             "OR (cast(:end as timestamp) NOT BETWEEN r.startDateTime AND r.endDateTime)")
    List<Integer> findAllBoatIdsThatWorkForPeriod(@Param("start") Date start, @Param("end") Date end);

    List<TermBoat> findByBoat(Boat boat);
}
