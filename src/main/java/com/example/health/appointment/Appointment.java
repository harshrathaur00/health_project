package com.example.health.appointment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointment {
	private static final long serialVersionUID = 6529685098267757691L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String patient_id;
	private String doctor_id;
	public enum Status {
		Requested, Scheduled, Completed, Cancelled
	}
	private Status status;
	private java.sql.Timestamp fromTime;
	private java.sql.Timestamp toTime;
	private String ailments;
	private java.sql.Timestamp createdAt;
	private java.sql.Timestamp updatedAt;
	public Appointment() {
			
	}
	public Appointment(String patient_id, String doctor_id, String ailments) {
		super();
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
		this.ailments = ailments;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public java.sql.Timestamp getFromTime() {
		return fromTime;
	}
	public void setFromTime(java.sql.Timestamp fromTime) {
		this.fromTime = fromTime;
	}
	public java.sql.Timestamp getToTime() {
		return toTime;
	}
	public void setToTime(java.sql.Timestamp toTime) {
		this.toTime = toTime;
	}
	public String getAilments() {
		return ailments;
	}
	public void setAilments(String ailments) {
		this.ailments = ailments;
	}
	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public java.sql.Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(java.sql.Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
