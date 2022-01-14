package com.springboot.app.model.dto;

public class WeekendHouseComplaintDTO {
    private Integer id;
    private String noteHouse;
    private String noteOwner;
    private Integer weekendHouseReservationId;

    public WeekendHouseComplaintDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteHouse() {
        return noteHouse;
    }

    public void setNoteHouse(String noteHouse) {
        this.noteHouse = noteHouse;
    }

    public String getNoteOwner() {
        return noteOwner;
    }

    public void setNoteOwner(String noteOwner) {
        this.noteOwner = noteOwner;
    }

    public Integer getWeekendHouseReservationId() {
        return weekendHouseReservationId;
    }

    public void setWeekendHouseReservationId(Integer weekendHouseReservationId) {
        this.weekendHouseReservationId = weekendHouseReservationId;
    }
}
