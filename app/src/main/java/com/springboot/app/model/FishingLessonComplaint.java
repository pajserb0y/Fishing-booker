package com.springboot.app.model;

import com.springboot.app.model.dto.FishingLessonComplaintDTO;

import javax.persistence.*;

@Entity
@Table(name = "fishing_lesson_complaints")
public class FishingLessonComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String noteFishingLesson;
    private String noteOwner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fishing_lesson_reservation_id")
    private FishingLessonReservation fishingLessonReservation;


    public FishingLessonComplaint() {
    }


    public FishingLessonComplaint(FishingLessonComplaintDTO dto, FishingLessonReservation fishingLessonReservation) {
        this.id = dto.getId();
        this.noteFishingLesson = dto.getNoteFishingLesson();
        this.noteOwner = dto.getNoteOwner();
        this.fishingLessonReservation = fishingLessonReservation;
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

    public FishingLessonReservation getFishingLessonReservation() {
        return fishingLessonReservation;
    }

    public void setFishingLessonReservation(FishingLessonReservation fishingLessonReservation) {
        this.fishingLessonReservation = fishingLessonReservation;
    }
}
