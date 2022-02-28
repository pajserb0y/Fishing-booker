package com.springboot.app.model.dto;

public class EntityIdAndCustomerUsername {
    private String username;
    private Integer id;

    public EntityIdAndCustomerUsername() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
