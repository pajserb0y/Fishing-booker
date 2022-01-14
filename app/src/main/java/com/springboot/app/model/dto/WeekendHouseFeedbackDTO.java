package com.springboot.app.model.dto;

import com.springboot.app.model.WeekendHouseReservation;


public class WeekendHouseFeedbackDTO {

    private Integer id;
    private Integer gradeHouse;
    private String noteHouse;
    private Integer gradeOwner;
    private String noteOwner;
    private Integer weekendHouseReservationId;


    public WeekendHouseFeedbackDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeHouse() {
        return gradeHouse;
    }

    public void setGradeHouse(Integer gradeHouse) {
        this.gradeHouse = gradeHouse;
    }

    public String getNoteHouse() {
        return noteHouse;
    }

    public void setNoteHouse(String noteHouse) {
        this.noteHouse = noteHouse;
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

    public Integer getWeekendHouseReservationId() {
        return weekendHouseReservationId;
    }

    public void setWeekendHouseReservationId(Integer weekendHouseReservation) {
        this.weekendHouseReservationId = weekendHouseReservation;
    }
}
