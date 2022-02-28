package com.springboot.app.model.dto;

import com.springboot.app.model.FishingLesson;
import com.springboot.app.model.TermFishingLesson;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class TermFishingLessonDTO {
    private Integer id;
    private Date startDateTime;
    private Date endDateTime;
    private FishingLessonDTO fishingLesson;


    public TermFishingLessonDTO() {
    }

    public TermFishingLessonDTO(TermFishingLesson term) {
        this.id = term.getId();
        this.startDateTime = term.getStartDateTime();
        this.endDateTime = term.getEndDateTime();
        this.fishingLesson = new FishingLessonDTO(term.getFishingLesson());
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

    public FishingLessonDTO getFishingLesson() {
        return fishingLesson;
    }

    public void setFishingLesson(FishingLessonDTO fishingLesson) {
        this.fishingLesson = fishingLesson;
    }
}
