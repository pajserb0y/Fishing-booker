package com.springboot.app.repository;

import com.springboot.app.model.BoatComplaint;
import com.springboot.app.model.WeekendHouseComplaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatComplaintRepository extends JpaRepository<BoatComplaint, Integer> {
}
