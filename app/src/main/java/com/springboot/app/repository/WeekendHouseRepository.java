package com.springboot.app.repository;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface WeekendHouseRepository extends JpaRepository<WeekendHouse,Integer> {

    List<WeekendHouse> findAllByIdNotIn(Collection<Integer> ids);

    List<WeekendHouse> findByWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner);


}
