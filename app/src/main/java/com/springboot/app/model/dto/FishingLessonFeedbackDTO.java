package com.springboot.app.model.dto;


public class FishingLessonFeedbackDTO {
    private Integer id;
    private Integer gradeFishingLesson;
    private String noteFishingLesson;
    private Integer gradeOwner;
    private String noteOwner;
    private Integer fishingLessonReservationId;

    private boolean isApproved;



    public FishingLessonFeedbackDTO() {
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

    public Integer getFishingLessonReservationId() {
        return fishingLessonReservationId;
    }

    public void setFishingLessonReservationId(Integer fishingLessonReservationId) {
        this.fishingLessonReservationId = fishingLessonReservationId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
