package com.springboot.app.model;

import com.springboot.app.model.dto.CustomerDTO;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.springframework.util.DigestUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please fill out first name")
    private String firstName;

    @NotEmpty(message = "Please fill out last name")
    private String lastName;

    @Email(message = "Email is in wrong format")
    @NotBlank(message = "Please fill out email")
    private String email;

    @NotEmpty(message = "Please fill out password")
    private String password;

    @NotEmpty(message = "Please fill out address")
    private String address;

    @NotEmpty(message = "Please fill out city")
    private String city;

    @NotEmpty(message = "Please fill out country")
    private String country;

    @NotEmpty(message = "Please fill out phone")
    private String phone;

    private boolean isDeleted = false;
    private boolean isActivated;
    private String hashCode;

    public Customer(int id, String firstName, String lastName, String email, String password, String address, String city, String country, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public Customer() { }

    public Customer(CustomerDTO customerDto) {
        this.id = customerDto.getId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
        this.password = customerDto.getPassword();
        this.address = customerDto.getAddress();
        this.city = customerDto.getCity();
        this.country = customerDto.getCountry();
        this.phone = customerDto.getPhone();
        this.hashCode = generateHashCode(this.password);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String generateHashCode(String password){
//        MessageDigest digest = null;
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
//        String sha256hex = HexBin.encode(hash);
//        return sha256hex;


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // Change this to UTF-16 if needed
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        String hex = String.format("%064x", new BigInteger(1, digest));
        return hex;



//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
//        try {
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//            byte[] hash = factory.generateSecret(spec).getEncoded();
//            return hash.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}


