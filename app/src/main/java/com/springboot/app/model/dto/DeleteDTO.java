package com.springboot.app.model.dto;

import javax.validation.constraints.NotEmpty;

public class DeleteDTO {
    @NotEmpty(message = "Please fill out first name")
    public int id;
    @NotEmpty(message = "Please fill out first name")
    public String note;
}
