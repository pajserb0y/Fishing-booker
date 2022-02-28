package com.springboot.app.model;

import com.springboot.app.model.dto.BoatComplaintDTO;

import javax.persistence.*;

@Entity
@Table(name = "boat_complaints")
public class BoatComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String noteBoat;
    private String noteOwner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_reservation_id")
    private BoatReservation boatReservation;


    public BoatComplaint() {
    }

    public BoatComplaint(BoatComplaintDTO dto, BoatReservation boatReservation) {
        this.id = dto.getId();
        this.noteBoat = dto.getNoteBoat();
        this.noteOwner = dto.getNoteOwner();
        this.boatReservation = boatReservation;
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

    public BoatReservation getBoatReservation() {
        return boatReservation;
    }

    public void setBoatReservation(BoatReservation boatReservation) {
        this.boatReservation = boatReservation;
    }
}
