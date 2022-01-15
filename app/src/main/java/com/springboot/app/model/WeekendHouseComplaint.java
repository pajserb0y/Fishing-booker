package com.springboot.app.model;

import com.springboot.app.model.dto.WeekendHouseComplaintDTO;

import javax.persistence.*;

@Entity
@Table(name = "weekend_house_complaints")
public class WeekendHouseComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String noteHouse;
    private String noteOwner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weekend_house_reservation_id")
    private WeekendHouseReservation weekendHouseReservation;


    public WeekendHouseComplaint() {
    }

    public WeekendHouseComplaint(WeekendHouseComplaintDTO dto, WeekendHouseReservation weekendHouseReservation) {
        this.id = dto.getId();
        this.noteHouse = dto.getNoteHouse();
        this.noteOwner = dto.getNoteOwner();
        this.weekendHouseReservation = weekendHouseReservation;
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

    public WeekendHouseReservation getWeekendHouseReservation() {
        return weekendHouseReservation;
    }

    public void setWeekendHouseReservation(WeekendHouseReservation weekendHouseReservation) {
        this.weekendHouseReservation = weekendHouseReservation;
    }
}
