package com.springboot.app.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ReportDTO {
    private int id;
    @NotEmpty(message = "Please fill out first name")
    private Integer reservationId;
    @NotEmpty(message = "Please fill out commnet")
    private String commnet;
    @NotBlank(message = "Please fill out deservesPenalty")
    private boolean deservesPenalty;
    @NotEmpty(message = "Please fill out didntShowUp")
    private boolean didntShowUp;

    public ReportDTO(int id, Integer reservationId, String commnet, boolean deservesPenalty, boolean didntShowUp) {
        this.id = id;
        this.reservationId = reservationId;
        this.commnet = commnet;
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

    public String getCommnet() {
        return commnet;
    }

    public void setCommnet(String commnet) {
        this.commnet = commnet;
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
