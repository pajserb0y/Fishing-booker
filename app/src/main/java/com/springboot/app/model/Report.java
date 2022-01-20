package com.springboot.app.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotEmpty(message = "Please fill out comment")
    protected Integer ownerId;

    @NotEmpty(message = "Please fill out comment")
    protected Integer customerId;

    @NotEmpty(message = "Please fill out comment")
    protected Integer propertyId;

    @NotEmpty(message = "Please fill out comment")
    protected String comment;

    @NotEmpty(message = "Please fill out last name")
    protected boolean deservesPenalty;

    @Email(message = "Email is in wrong format")
    @NotBlank(message = "Please fill out email")
    protected boolean didntShowUp;

    @NotEmpty(message = "Please fill out username")
    protected String type; //boat/house/fishing

    public Report(Integer id, Integer ownerId, Integer customerId, Integer propertyId, String comment, boolean deservesPenalty, boolean didntShowUp, String type) {
        this.id = id;
        this.ownerId = ownerId;
        this.customerId = customerId;
        this.propertyId = propertyId;
        this.comment = comment;
        this.deservesPenalty = deservesPenalty;
        this.didntShowUp = didntShowUp;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
