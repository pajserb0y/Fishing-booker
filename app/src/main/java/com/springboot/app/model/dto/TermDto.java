package com.springboot.app.model.dto;

import com.springboot.app.model.Term;
import com.springboot.app.model.WeekendHouse;

import java.util.Date;
import java.util.Set;

public class TermDto {
    private Integer id;
    private Date startDateTime;
    private Date endDateTime;
    private WeekendHouseDTO weekendHouse;

    public TermDto()
    {

    }

    public TermDto(Integer id, Date startDateTime, Date endDateTime, WeekendHouseDTO weekendHouse) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.weekendHouse = weekendHouse;
    }

    public TermDto(Term freeTerm) {
        this.id = freeTerm.getId();
        this.startDateTime = freeTerm.getStartDateTime();
        this.endDateTime = freeTerm.getEndDateTime();
        this.weekendHouse = new WeekendHouseDTO(freeTerm.getWeekendHouse());
    }

    public Integer getId() {
        return id;
    }

    public WeekendHouseDTO getWeekendHouse() {
        return weekendHouse;
    }

    public void setWeekendHouse(WeekendHouseDTO weekendHouse) {
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
