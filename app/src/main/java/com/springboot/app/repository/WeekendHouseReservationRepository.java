package com.springboot.app.repository;

import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.WeekendHouseReservation;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.model.dto.WeekendHouseReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface WeekendHouseReservationRepository extends JpaRepository<WeekendHouseReservation,Integer> {

    @Query("SELECT r.weekendHouse.id FROM WeekendHouseReservation r WHERE (cast(:start as timestamp) BETWEEN r.startDateTime AND r.endDateTime) " +
            "OR (cast(:end as timestamp) BETWEEN r.startDateTime AND r.endDateTime)")
    List<Integer> findAllForDateRange(@Param("start") Date start, @Param("end") Date end);

    List<WeekendHouseReservation> findAllByCustomerUsername(String username);
}
