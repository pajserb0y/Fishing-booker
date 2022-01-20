package com.springboot.app.repository;

import com.springboot.app.model.Boat;
import com.springboot.app.model.BoatOwner;
import com.springboot.app.model.WeekendHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BoatRepository extends JpaRepository<Boat,Integer> {

    List<Boat> findAllByIdNotIn(Collection<Integer> ids);

    List<Boat> findByBoatOwner(BoatOwner boatOwner);
}
