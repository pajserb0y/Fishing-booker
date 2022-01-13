package com.springboot.app.repository;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WeekendHouseReservationRepository extends JpaRepository<WeekendHouseReservation,Integer> {

    @Query("SELECT r.weekendHouse.id FROM WeekendHouseReservation r WHERE (cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
            "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime)")
    List<Integer> findAllForDateRange(@Param("start") Date start, @Param("end") Date end);

    List<WeekendHouseReservation> findAllByCustomerUsername(String username);
    //@Query("SELECT r FROM WeekendHouseReservation r WHERE r.weekendHouse.id == id")
    List<WeekendHouseReservation> findByWeekendHouse( WeekendHouse weekendHouse);
}
