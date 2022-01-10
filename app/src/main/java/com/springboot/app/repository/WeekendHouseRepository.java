package com.springboot.app.repository;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekendHouseRepository extends JpaRepository<WeekendHouse,Integer> {
}
