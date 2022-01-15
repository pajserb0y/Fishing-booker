package com.springboot.app.model;

//import com.springboot.app.model.dto.TermBoatDTO;
//import com.springboot.app.model.dto.TermDto;

import com.springboot.app.model.dto.TermFishingLessonDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TermFishingLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date startDateTime;
    private Date endDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fishing_lesson_id")
    private FishingLesson fishingLesson;


    public TermFishingLesson() {
    }

    public TermFishingLesson(TermFishingLessonDTO dto) {
        this.id = dto.getId();
        this.startDateTime = dto.getStartDateTime();
        this.endDateTime = dto.getEndDateTime();
        this.fishingLesson = new FishingLesson(dto.getFishingLesson());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public FishingLesson getFishingLesson() {
        return fishingLesson;
    }

    public void setFishingLesson(FishingLesson fishingLesson) {
        this.fishingLesson = fishingLesson;
    }
}
