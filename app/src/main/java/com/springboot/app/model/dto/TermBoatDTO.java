package com.springboot.app.model.dto;

import com.springboot.app.model.Term;
import com.springboot.app.model.TermBoat;

import java.util.Date;

public class TermBoatDTO {
    private Integer id;
    private Date startDateTime;
    private Date endDateTime;
    private BoatDTO boat;

    public TermBoatDTO() {
    }

    public TermBoatDTO(TermBoat freeTerm) {
        this.id = freeTerm.getId();
        this.startDateTime = freeTerm.getStartDateTime();
        this.endDateTime = freeTerm.getEndDateTime();
        this.boat = new BoatDTO(freeTerm.getBoat());
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

    public BoatDTO getBoat() {
        return boat;
    }

    public void setBoat(BoatDTO boat) {
        this.boat = boat;
    }
}
