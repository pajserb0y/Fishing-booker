package com.springboot.app.repository;

import com.springboot.app.model.WeekendHouseComplaint;
import com.springboot.app.model.WeekendHouseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekendHouseComplaintRepository extends JpaRepository<WeekendHouseComplaint, Integer> {

}
