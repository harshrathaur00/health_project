package com.example.health.appointment;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public List<Appointment> getDoctorAppointments(String doctorid,Timestamp from,Timestamp to) {
		List<Appointment> appointments=new ArrayList<>();
		appointmentRepository.findDoctorAppointments(doctorid,from,to)
		.forEach(appointments::add);
		return appointments;
	}
	public List<Appointment> getPatientAppointments(String patientid,Timestamp from,Timestamp to) {
		List<Appointment> appointments=new ArrayList<>();
		appointmentRepository.findPatientAppointments(patientid,from,to)
		.forEach(appointments::add);
		return appointments;
	}
	public Appointment getAppointment(Long id) throws Exception {
		Optional<Appointment> apt=appointmentRepository.findById(id);
		//System.out.println("GetDept by id method, db hit");
		//System.out.println(dept);
		if(apt.get()==null) throw new Exception("Not found");
		return apt.get();
	}
	
	public Appointment addAppointment(Appointment apt) {
		apt.setStatus(Appointment.Status.Requested);
		apt.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		apt.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		return appointmentRepository.save(apt);
	}
	
	public Appointment setAppointmentSlot(Timestamp from, Timestamp to, Long id) throws Exception {
		//System.out.println(from+" "+to);
		//appointmentRepository.setSlot(from,to,id);
		Optional<Appointment> aptOp=appointmentRepository.findById(id);
		Appointment apt=aptOp.get();
		if(apt==null) throw new Exception("Not Found");
		apt.setFromTime(from);
		apt.setToTime(to);
		apt.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		apt.setStatus(Appointment.Status.Scheduled);
		appointmentRepository.save(apt);
		return apt;
		
	}
	
	public Appointment cancelAppointment(Long appointment_id) throws Exception {
		Optional<Appointment> aptOp=appointmentRepository.findById(appointment_id);
		Appointment apt=aptOp.get();
		if(apt==null) throw new Exception("Not Found");
		apt.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		apt.setStatus(Appointment.Status.Cancelled);
		appointmentRepository.save(apt);
		return apt;
	}
	
	public void deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}
	

}
