package com.example.health.appointment;

import java.sql.Timestamp;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.health.BaseController;
import com.example.health.BaseResponse;

@RestController
public class AppointmentController extends BaseController {
	//public final String HashKey="Dept_Emp";
	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping("/test")
	public ResponseEntity<BaseResponse> test() {
		//System.out.println("Test API");
		BaseResponse bR=buildResponse("Success","Test Successful","200",new Appointment());
		//return (ResponseEntity<BaseResponse>) ResponseEntity.status(HttpStatus.OK).body(bR);
		System.out.println(bR);
		return new ResponseEntity(bR,HttpStatus.OK);
	}
	
	@RequestMapping("/doctor/appointment/{doctorid}/{from}/{to}")
	public List<Appointment> getDocotorAppointments(@PathVariable String doctorid,@PathVariable Timestamp from,@PathVariable Timestamp to) {
		return appointmentService.getDocotorAppointments(doctorid,from,to);
	}
	
	@RequestMapping("/patient/appointment/{patientid}/{from}/{to}")
	public List<Appointment> getPatientAppointments(@PathVariable String patientid,@PathVariable Timestamp from,@PathVariable Timestamp to) {
		return appointmentService.getPatientAppointments(patientid,from,to);
	}
	@RequestMapping("/appointment/{id}")
	//@Cacheable(key="#id",value=HashKey)
	public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
		//System.out.println("GetDept by id method"+id);
		try {
			Appointment apt=appointmentService.getAppointment(id);
			return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.OK).body(apt);
		}catch(Exception e) {
			///return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Appointment());
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/patient/appointment")
	public ResponseEntity<String> addDepartment(@RequestBody Appointment apt) {
		
		try{
			appointmentService.addAppointment(apt);
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.CREATED).body("Successfully added");
		}catch(Exception e) {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add request");
		}
			
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/doctor/appointment/{appointment_id}/{from}/{to}")
	//@CachePut(key="#id",value=HashKey)
	
	public ResponseEntity<Appointment> setAppointmentSlot(@PathVariable Long appointment_id,@PathVariable Timestamp from,@PathVariable Timestamp to) {
		try {
			Appointment apt=appointmentService.setAppointmentSlot(from,to,appointment_id);
			return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.OK).body(apt);
		}catch(Exception e) {
			//return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Appointment());
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/patient/appointment/cancel/{appointment_id}")
	//@CachePut(key="#id",value=HashKey)
	public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long appointment_id) {
		try {
			Appointment apt=appointmentService.cancelAppointment(appointment_id);
			return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.OK).body(apt);
		}catch(Exception e) {
			//return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Appointment());
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/appointment/{appointment_id}")
	//@CacheEvict(key="#id",value=HashKey)
	public ResponseEntity<String> deleteDepartment(@PathVariable Long appointment_id) {
		try {
			appointmentService.deleteAppointment(appointment_id);
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK).body("Deleted");
		}catch(Exception e) {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
		}
	}
	
}
