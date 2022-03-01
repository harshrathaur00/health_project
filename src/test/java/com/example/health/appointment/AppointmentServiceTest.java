package com.example.health.appointment;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AppointmentServiceTest {

	@InjectMocks
	AppointmentService appointmentService;
	@Mock
	AppointmentRepository appointmentRepository;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetAppointment() {
		Appointment apt=new Appointment();
		apt.setId((long) 0);
		apt.setDoctor_id("xyz");
		apt.setPatient_id("pqr");
		apt.setStatus(Appointment.Status.Requested);
		apt.setAilments("a,b,c,d");
		
		
		when(appointmentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(apt));
		try {
			Appointment AptRes=appointmentService.getAppointment((long)0);
			assertNotNull(AptRes);
			assertEquals("xyz",AptRes.getDoctor_id());
			assertEquals("pqr",AptRes.getPatient_id());
			assertEquals(Appointment.Status.Requested,AptRes.getStatus());
			assertEquals("a,b,c,d",AptRes.getAilments());
			assertEquals((long)0,AptRes.getId());
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	void testGetDoctorAppointments() {
		Appointment apt1=new Appointment();
		apt1.setId((long) 0);
		apt1.setDoctor_id("xyz");
		apt1.setPatient_id("pqr");
		apt1.setStatus(Appointment.Status.Requested);
		apt1.setAilments("a,b,c,d");
		
		Appointment apt2=new Appointment();
		apt2.setId((long) 0);
		apt2.setDoctor_id("xyz");
		apt2.setPatient_id("pqr");
		apt2.setStatus(Appointment.Status.Requested);
		apt2.setAilments("a,b,c,d");
		Appointment arr[]= {apt1,apt2};
		List<Appointment> appts=Arrays.asList(arr);
		when(appointmentRepository.findDoctorAppointments(anyString(),any(),any())).thenReturn(appts);
		
		List<Appointment> AptsRes=appointmentService.getDoctorAppointments("xyz",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		assertNotNull(AptsRes);
		assertEquals(2,AptsRes.size());
		assertEquals("xyz",AptsRes.get(0).getDoctor_id());
		assertEquals("pqr",AptsRes.get(0).getPatient_id());
		assertEquals(Appointment.Status.Requested,AptsRes.get(0).getStatus());
		assertEquals("a,b,c,d",AptsRes.get(0).getAilments());
		
	}
	
	@Test
	void testGetPatientAppointments() {
		Appointment apt1=new Appointment();
		apt1.setId((long) 0);
		apt1.setDoctor_id("xyz");
		apt1.setPatient_id("pqr");
		apt1.setStatus(Appointment.Status.Requested);
		apt1.setAilments("a,b,c,d");
		
		Appointment apt2=new Appointment();
		apt2.setId((long) 0);
		apt2.setDoctor_id("xyz");
		apt2.setPatient_id("pqr");
		apt2.setStatus(Appointment.Status.Requested);
		apt2.setAilments("a,b,c,d");
		Appointment arr[]= {apt1,apt2};
		List<Appointment> appts=Arrays.asList(arr);
		when(appointmentRepository.findPatientAppointments(anyString(),any(),any())).thenReturn(appts);
		
		List<Appointment> AptsRes=appointmentService.getPatientAppointments("xyz",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		assertNotNull(AptsRes);
		assertEquals(2,AptsRes.size());
		assertEquals("xyz",AptsRes.get(0).getDoctor_id());
		assertEquals("pqr",AptsRes.get(0).getPatient_id());
		assertEquals(Appointment.Status.Requested,AptsRes.get(0).getStatus());
		assertEquals("a,b,c,d",AptsRes.get(0).getAilments());
		
	}
	
	@Test
	void testAddAppointment() {
		Appointment apt=new Appointment();
		apt.setId((long) 0);
		apt.setDoctor_id("xyz");
		apt.setPatient_id("pqr");
		//apt.setStatus(Appointment.Status.Requested);
		apt.setAilments("a,b,c,d");
		when(appointmentRepository.save(any())).thenReturn(apt);
		Appointment AptRes=appointmentService.addAppointment(apt);
		assertEquals(Appointment.Status.Requested,AptRes.getStatus());
		assertNotNull(AptRes.getCreatedAt());
		assertNotNull(AptRes.getUpdatedAt());
		assertEquals("xyz",AptRes.getDoctor_id());
		assertEquals("pqr",AptRes.getPatient_id());
		assertEquals("a,b,c,d",AptRes.getAilments());
		assertEquals((long)0,AptRes.getId());
	}
	
	@Test
	void testSetAppointmentSlot() {
		Appointment apt=new Appointment();
		apt.setId((long) 0);
		apt.setDoctor_id("xyz");
		apt.setPatient_id("pqr");
		apt.setAilments("a,b,c,d");
		when(appointmentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(apt));
		when(appointmentRepository.save(any())).thenReturn(apt);
		Timestamp from=new Timestamp(System.currentTimeMillis());
		Timestamp to=new Timestamp(from.getTime() + (1000 * 60 * 60 * 3));
		try {
			Appointment AptRes=appointmentService.setAppointmentSlot(from,to,(long)0);
			assertEquals(Appointment.Status.Scheduled,AptRes.getStatus());
			assertNotNull(AptRes.getUpdatedAt());
			assertEquals("xyz",AptRes.getDoctor_id());
			assertEquals("pqr",AptRes.getPatient_id());
			assertEquals("a,b,c,d",AptRes.getAilments());
			assertEquals((long)0,AptRes.getId());
			assertEquals(from,AptRes.getFromTime());
			assertEquals(to,AptRes.getToTime());
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	void testCancelAppointment() {
		Appointment apt=new Appointment();
		apt.setId((long) 0);
		apt.setDoctor_id("xyz");
		apt.setPatient_id("pqr");
		apt.setAilments("a,b,c,d");
		when(appointmentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(apt));
		when(appointmentRepository.save(any())).thenReturn(apt);
		try {
			Appointment AptRes=appointmentService.cancelAppointment((long)0);
			assertEquals(Appointment.Status.Cancelled,AptRes.getStatus());
			assertNotNull(AptRes.getUpdatedAt());
			assertEquals("xyz",AptRes.getDoctor_id());
			assertEquals("pqr",AptRes.getPatient_id());
			assertEquals("a,b,c,d",AptRes.getAilments());
			assertEquals((long)0,AptRes.getId());
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	

}
