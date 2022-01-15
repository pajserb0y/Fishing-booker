package com.springboot.app.model;

import com.springboot.app.model.dto.BoatFeedbackDTO;

import javax.persistence.*;

@Entity
@Table(name = "boat_feedbacks")
public class BoatFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer gradeBoat;
    private String noteBoat;
    private Integer gradeOwner;
    private String noteOwner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_reservation_id")
    private BoatReservation boatReservation;

    private boolean isApproved;


    public BoatFeedback() {
    }

    public BoatFeedback(BoatFeedbackDTO dto, BoatReservation boatReservation) {
        this.id = dto.getId();
        this.gradeBoat = dto.getGradeBoat();
        this.noteBoat = dto.getNoteBoat();
        this.gradeOwner = dto.getGradeOwner();
        this.noteOwner = dto.getNoteOwner();
        this.boatReservation = boatReservation;
        this.isApproved = false;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public BoatReservation getBoatReservation() {
        return boatReservation;
    }

    public void setBoatReservation(BoatReservation boatReservation) {
        this.boatReservation = boatReservation;
    }
}
