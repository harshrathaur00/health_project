package com.example.health.appointment;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment,Long > {
	@Query("select a from Appointment a where a.doctor_id = ?1 and a.fromTime>=?2 and a.toTime<=?3")
	public List<Appointment> findDoctorAppointments(String doctorid,Timestamp from, Timestamp to);
	
	@Query("select a from Appointment a where a.patient_id = ?1 and a.fromTime>=?2 and a.toTime<=?3")
	public List<Appointment> findPatientAppointments(String patientid,Timestamp from, Timestamp to);
	
	//@Query("update Appointment a set a.fromTime=?1,a.toTime=?2 where a.id = ?3")
	//public void setSlot(Timestamp from, Timestamp to,Long id);
}