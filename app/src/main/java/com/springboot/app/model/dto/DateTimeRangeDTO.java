package com.springboot.app.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeRangeDTO {
    private Date start;
    private Date end;

    public DateTimeRangeDTO() {
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }


    public static Date ConvertFromStringToDate(String dto) {
        String[] mainParts = dto.split("T");

        String[] dateParts = mainParts[0].split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        String[] timeParts = mainParts[1].split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        int second = Integer.parseInt(timeParts[2].substring(0, 2));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(year, month, day, hour, minute, second);
        //Date date = simpleDateFormat.format(d);

        return date;
    }
}
