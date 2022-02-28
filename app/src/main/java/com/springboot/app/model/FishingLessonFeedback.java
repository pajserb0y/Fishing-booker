package com.springboot.app.model;

import com.springboot.app.model.dto.FishingLessonFeedbackDTO;

import javax.persistence.*;

@Entity
@Table(name = "fishing_lesson_feedbacks")
public class FishingLessonFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer gradeFishingLesson;
    private String noteFishingLesson;
    private Integer gradeOwner;
    private String noteOwner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fishing_lesson_reservation_id")
    private FishingLessonReservation fishingLessonReservation;

    private boolean isApproved;


    public FishingLessonFeedback() {
    }

    public FishingLessonFeedback(FishingLessonFeedbackDTO dto, FishingLessonReservation fishingLessonReservation) {
        this.id = dto.getId();
        this.gradeFishingLesson = dto.getGradeFishingLesson();
        this.noteFishingLesson = dto.getNoteFishingLesson();
        this.gradeOwner = dto.getGradeOwner();
        this.noteOwner = dto.getNoteOwner();
        this.fishingLessonReservation = fishingLessonReservation;
        this.isApproved = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeFishingLesson() {
        return gradeFishingLesson;
    }

    public void setGradeFishingLesson(Integer gradeFishingLesson) {
        this.gradeFishingLesson = gradeFishingLesson;
    }

    public String getNoteFishingLesson() {
        return noteFishingLesson;
    }

    public void setNoteFishingLesson(String noteFishingLesson) {
        this.noteFishingLesson = noteFishingLesson;
    }

    public Integer getGradeOwner() {
        return gradeOwner;
    }

    public void setGradeOwner(Integer gradeOwner) {
        this.gradeOwner = gradeOwner;
    }

    public String getNoteOwner() {
        return noteOwner;
    }

    public void setNoteOwner(String noteOwner) {
        this.noteOwner = noteOwner;
    }

    public FishingLessonReservation getFishingLessonReservation() {
        return fishingLessonReservation;
    }

    public void setFishingLessonReservation(FishingLessonReservation fishingLessonReservation) {
        this.fishingLessonReservation = fishingLessonReservation;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
