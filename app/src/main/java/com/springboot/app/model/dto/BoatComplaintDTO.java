package com.springboot.app.model.dto;

public class BoatComplaintDTO {
    private Integer id;
    private String noteBoat;
    private String noteOwner;
    private Integer boatReservationId;

    public BoatComplaintDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteBoat() {
        return noteBoat;
    }

    public void setNoteBoat(String noteBoat) {
        this.noteBoat = noteBoat;
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
}
