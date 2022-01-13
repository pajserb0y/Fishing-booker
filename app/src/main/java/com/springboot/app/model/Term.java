package com.springboot.app.model;

import com.springboot.app.model.dto.TermDto;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date startDateTime;
    private Date endDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weekend_house_id")
    private WeekendHouse weekendHouse;

    public Term() {
    }
    public Term(TermDto dto) {
        this.id = dto.getId();
        this.startDateTime = dto.getStartDateTime();
        this.endDateTime = dto.getEndDateTime();
        this.weekendHouse = dto.getWeekendHouse();
    }
    public Term(Integer id, Date startDateTime, Date endDateTime, WeekendHouse weekendHouse) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.weekendHouse = weekendHouse;
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

    public WeekendHouse getWeekendHouse() {
        return weekendHouse;
    }

    public void setWeekendHouse(WeekendHouse weekendHouse) {
        this.weekendHouse = weekendHouse;
    }
}
