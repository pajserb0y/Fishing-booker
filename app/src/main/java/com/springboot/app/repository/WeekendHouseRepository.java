package com.springboot.app.repository;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface WeekendHouseRepository extends JpaRepository<WeekendHouse,Integer> {

    @Query("SELECT r FROM WeekendHouse r WHERE r.id IN ?1")
    List<WeekendHouse> findAvailable(List<Integer> weekendHouseIds);
}
