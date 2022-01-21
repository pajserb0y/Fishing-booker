package com.springboot.app.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ReportDTO {
    private Integer id;
    @NotEmpty(message = "Please fill out first name")
    private Integer reservationId;
    @NotEmpty(message = "Please fill out commnet")
    private String comment;
    @NotBlank(message = "Please fill out deservesPenalty")
    private boolean deservesPenalty;
    @NotEmpty(message = "Please fill out didntShowUp")
    private boolean didntShowUp;

    public ReportDTO() {

    }

    public ReportDTO(int id, Integer reservationId, String comment, boolean deservesPenalty, boolean didntShowUp) {
        this.id = id;
        this.reservationId = reservationId;
        this.comment = comment;
        this.deservesPenalty = deservesPenalty;
        this.didntShowUp = didntShowUp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDeservesPenalty() {
        return deservesPenalty;
    }

    public void setDeservesPenalty(boolean deservesPenalty) {
        this.deservesPenalty = deservesPenalty;
    }

    public boolean isDidntShowUp() {
        return didntShowUp;
    }

    public void setDidntShowUp(boolean didntShowUp) {
        this.didntShowUp = didntShowUp;
    }
}
