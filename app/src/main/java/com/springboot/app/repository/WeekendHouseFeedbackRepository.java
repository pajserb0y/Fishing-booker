package com.springboot.app.repository;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekendHouseFeedbackRepository extends JpaRepository<WeekendHouseFeedback, Integer> {

}
