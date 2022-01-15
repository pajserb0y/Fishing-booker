package com.springboot.app.model;

import com.springboot.app.model.dto.WeekendHouseFeedbackDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weekend_house_feedbacks")
public class WeekendHouseFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer gradeHouse;
    private String noteHouse;
    private Integer gradeOwner;
    private String noteOwner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weekend_house_reservation_id")
    private WeekendHouseReservation weekendHouseReservation;

    private boolean isApproved;


    public WeekendHouseFeedback() {
    }

    public WeekendHouseFeedback(WeekendHouseFeedbackDTO dto, WeekendHouseReservation weekendHouseReservation) {
        this.id = dto.getId();
        this.gradeHouse = dto.getGradeHouse();
        this.noteHouse = dto.getNoteHouse();
        this.gradeOwner = dto.getGradeOwner();
        this.noteOwner = dto.getNoteOwner();
        this.weekendHouseReservation = weekendHouseReservation;
        this.isApproved = false;
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

    public WeekendHouseReservation getWeekendHouseReservation() {
        return weekendHouseReservation;
    }

    public void setWeekendHouseReservation(WeekendHouseReservation weekendHouseReservation) {
        this.weekendHouseReservation = weekendHouseReservation;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
