package com.springboot.app.model.dto;

import com.springboot.app.model.BoatReservation;

public class BoatFeedbackDTO {
    private Integer id;
    private Integer gradeBoat;
    private String noteBoat;
    private Integer gradeOwner;
    private String noteOwner;
    private Integer boatReservationId;

    private boolean isApproved;


    public BoatFeedbackDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeBoat() {
        return gradeBoat;
    }

    public void setGradeBoat(Integer gradeBoat) {
        this.gradeBoat = gradeBoat;
    }

    public String getNoteBoat() {
        return noteBoat;
    }

    public void setNoteBoat(String noteBoat) {
        this.noteBoat = noteBoat;
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

    public Integer getBoatReservationId() {
        return boatReservationId;
    }

    public void setBoatReservationId(Integer boatReservationId) {
        this.boatReservationId = boatReservationId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
