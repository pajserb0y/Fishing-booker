package com.springboot.app.repository;

import com.springboot.app.model.WeekendHouseOwner;
import com.springboot.app.model.WeekendHouseReservation;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface WeekendHouseReservationRepository extends JpaRepository<WeekendHouseReservation,Integer> {

    @Query("SELECT r.id FROM WeekendHouseReservation r WHERE r.startDateTime >= ?1 AND r.endDateTime <= ?2")
    List<Integer> findAllForDateRange(Date start, Date end);
}
