package com.springboot.app.model.dto;

public class FishingLessonComplaintDTO {
    private Integer id;
    private String noteFishingLesson;
    private String noteOwner;
    private Integer fishingLessonReservationId;


    public FishingLessonComplaintDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteFishingLesson() {
        return noteFishingLesson;
    }

    public void setNoteFishingLesson(String noteFishingLesson) {
        this.noteFishingLesson = noteFishingLesson;
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
}
