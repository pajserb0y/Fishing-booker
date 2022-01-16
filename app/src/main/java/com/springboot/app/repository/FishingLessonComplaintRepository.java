package com.springboot.app.repository;

import com.springboot.app.model.BoatComplaint;
import com.springboot.app.model.FishingLessonComplaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingLessonComplaintRepository extends JpaRepository<FishingLessonComplaint, Integer> {
}
