package com.springboot.app.model;

import com.springboot.app.model.dto.ReportDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected Integer ownerId;

    protected Integer customerId;

    protected Integer propertyId;

    protected String comment;

    protected boolean deservesPenalty;

    protected boolean didntShowUp;

    protected String type; //boat/house/fishing

    public Report() {

    }

    public Report(ReportDTO dto, Integer ownerId, Integer customerId, Integer propertyId, String type) {
        this.ownerId = ownerId;
        this.customerId = customerId;
        this.propertyId = propertyId;
        this.comment = dto.getComment();
        this.deservesPenalty = dto.isDeservesPenalty();
        this.didntShowUp = dto.isDidntShowUp();
        this.type = type;
    }

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
