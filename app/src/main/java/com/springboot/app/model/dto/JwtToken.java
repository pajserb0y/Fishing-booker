package com.springboot.app.model.dto;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class JwtToken {

    private String accessToken;
    private Long expiresIn;

    public JwtToken() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public JwtToken(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;

    }
}
