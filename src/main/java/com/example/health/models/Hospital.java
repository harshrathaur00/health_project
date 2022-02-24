package com.example.health.models;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Hospital 
{
    @Id
    @Column(name = "id", nullable = false)

    private String id;

    private String name;
    private String address;
    private String city ;
    private int pincode;
    private String email;
    
    private String username;
    private String password;




    public Hospital() {
    }

    public Hospital(String id, String name, String address, String city, int pincode, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
