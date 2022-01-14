package com.springboot.app.model.dto;

public class WeekendHouseWithAvgGradeDTO {
    private WeekendHouseDTO weekendHouse;
    private Integer avgGrade;

    public WeekendHouseWithAvgGradeDTO() {
    }

    public WeekendHouseDTO getWeekendHouse() {
        return weekendHouse;
    }

    public void setWeekendHouse(WeekendHouseDTO weekendHouse) {
        this.weekendHouse = weekendHouse;
    }

    public Integer getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Integer avgGrade) {
        this.avgGrade = avgGrade;
    }
}
