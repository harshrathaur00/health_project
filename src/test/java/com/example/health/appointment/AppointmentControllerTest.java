package com.example.health.appointment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class AppointmentControllerTest {

	@InjectMocks
	AppointmentController departmentController;
	@Mock
	AppointmentService appointmentService;
	Appointment apt;
	public final String CONTEXT_PATH="";
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	final void testGetAppointment() {
		apt=new Appointment();
		apt.setId((long)0);
		apt.setDoctor_id("xyz");
		apt.setPatient_id("pqr");
		apt.setStatus(Appointment.Status.Requested);
		apt.setAilments("a,b,c,d");
		try {
			when(appointmentService.getAppointment(anyLong())).thenReturn(apt);
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
	
	/*
	@Test
	final void testPostDepartment() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
		Map<String,Object> deptDtls=new HashMap<>();
		deptDtls.put("name", "Tech User Platform");
		deptDtls.put("location", "Virtual");
		Response response = given().
		contentType("application/json").
		accept("application/json").
		body(deptDtls).
		when().
		post(CONTEXT_PATH).
		then().
		statusCode(200).
		contentType("").
		extract().
		response();
		//assertNull(response.jsonPath());
	}
	*/
	

}
