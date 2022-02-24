package com.example.health.models;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Specialization
{
    @Id
    @NonNull
    private String s_id;

    private String name;

    @OneToMany
    @JoinColumn(name = "doctor_id")
    private List<Doctor> doctor;

    public void setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
    }

    public List<Doctor> getDoctor() {
        return doctor;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String id) {
        this.s_id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
