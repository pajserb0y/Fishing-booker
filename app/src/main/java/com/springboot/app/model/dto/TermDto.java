package com.springboot.app.model.dto;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouse;

import java.util.Date;
import java.util.Set;

public class TermDto {
    private Integer id;
    private Date startDateTime;
    private Date endDateTime;
    private WeekendHouse weekendHouse;

    public TermDto()
    {

    }

    public TermDto(Integer id, Date startDateTime, Date endDateTime, WeekendHouse weekendHouse) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.weekendHouse = weekendHouse;
    }

    public TermDto(Term freeTerm) {
        this.id = freeTerm.getId();
        this.startDateTime = freeTerm.getStartDateTime();
        this.endDateTime = freeTerm.getEndDateTime();
        this.weekendHouse = freeTerm.getWeekendHouse();
    }

    public Integer getId() {
        return id;
    }

    public WeekendHouse getWeekendHouse() {
        return weekendHouse;
    }

    public void setWeekendHouse(WeekendHouse weekendHouse) {
        this.weekendHouse = weekendHouse;
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
}
