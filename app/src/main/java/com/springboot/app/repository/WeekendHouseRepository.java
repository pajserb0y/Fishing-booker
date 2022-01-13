package com.springboot.app.repository;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface WeekendHouseRepository extends JpaRepository<WeekendHouse,Integer> {

//    @Query("SELECT r FROM WeekendHouse r WHERE r.id NOT IN (:ids)")
//    List<WeekendHouse> findAvailable(@Param("ids") List<Integer> weekendHouseIds);
    List<WeekendHouse> findAllByIdNotIn(Collection<Integer> ids);
   // @Query("SELECT r FROM WeekendHouse r WHERE r.id IN ?1")
   // List<WeekendHouse> findAvailable(List<Integer> weekendHouseIds);

    List<WeekendHouse> findByWeekendHouseOwner(WeekendHouseOwner weekendHouseOwner);
}
