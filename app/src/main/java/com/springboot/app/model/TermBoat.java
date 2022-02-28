package com.springboot.app.model;

import com.springboot.app.model.dto.TermBoatDTO;
import com.springboot.app.model.dto.TermDto;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TermBoat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date startDateTime;
    private Date endDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_id")
    private Boat boat;


    public TermBoat() {
    }

    public TermBoat(TermBoatDTO dto) {
        this.id = dto.getId();
        this.startDateTime = dto.getStartDateTime();
        this.endDateTime = dto.getEndDateTime();
        this.boat = new Boat(dto.getBoat());
    }

    public TermBoat(Integer id, Date startDateTime, Date endDateTime, Boat boat) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.boat = boat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
