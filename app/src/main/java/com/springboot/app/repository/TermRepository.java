package com.springboot.app.repository;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Integer> {
     List<Term> findByWeekendHouse(WeekendHouse weekendHouse);
}
