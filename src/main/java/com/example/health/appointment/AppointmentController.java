package com.example.health.appointment;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<BaseResponse> getDocotorAppointments(@PathVariable String doctorid,@PathVariable Timestamp from,@PathVariable Timestamp to) {
		List<Appointment> appointments= appointmentService.getDoctorAppointments(doctorid,from,to);
		BaseResponse b_response=buildResponse("Success","List of appointments","200",appointments);
		return new ResponseEntity(b_response,HttpStatus.OK);
	}
	
	@RequestMapping("/patient/appointment/{patientid}/{from}/{to}")
	public ResponseEntity<BaseResponse> getPatientAppointments(@PathVariable String patientid,@PathVariable Timestamp from,@PathVariable Timestamp to) {
		List<Appointment> appointments=appointmentService.getPatientAppointments(patientid,from,to);
		BaseResponse b_response=buildResponse("Success","List of appointments","200",appointments);
		return new ResponseEntity(b_response,HttpStatus.OK);
	}
	@RequestMapping("/appointment/{id}")
	//@Cacheable(key="#id",value=HashKey)
	public ResponseEntity<BaseResponse> getAppointment(@PathVariable Long id) {
		//System.out.println("GetDept by id method"+id);
		try {
			Appointment apt=appointmentService.getAppointment(id);
			BaseResponse b_response=buildResponse("Success","Appointment record fetched","200",apt);
			return new ResponseEntity(b_response,HttpStatus.OK);
		}catch(Exception e) {
			///return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Appointment());
			BaseResponse b_response=buildResponse("Failed","Appointment record not found","404",null);
			return new ResponseEntity(b_response,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/patient/appointment")
	public ResponseEntity<BaseResponse> addAppointment(@RequestBody Appointment apt) {
		
		try{
			Appointment apt_res=appointmentService.addAppointment(apt);
			BaseResponse b_response=buildResponse("Success","Appointment record saved","201",apt_res);
			return new ResponseEntity(b_response,HttpStatus.CREATED);
		}catch(Exception e) {
			BaseResponse b_response=buildResponse("Failed","Failed to save appointment record","500",null);
			return new ResponseEntity(b_response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/doctor/appointment/{appointment_id}/{from}/{to}")
	//@CachePut(key="#id",value=HashKey)
	
	public ResponseEntity<BaseResponse> setAppointmentSlot(@PathVariable Long appointment_id,@PathVariable Timestamp from,@PathVariable Timestamp to) {
		try {
			Appointment apt=appointmentService.setAppointmentSlot(from,to,appointment_id);
			BaseResponse b_response=buildResponse("Success","Slot updated successfully","200",apt);
			return new ResponseEntity(b_response,HttpStatus.OK);
		}catch(Exception e) {
			//return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Appointment());
			BaseResponse b_response=buildResponse("Failed","Appointment not found","404",null);
			return new ResponseEntity(b_response,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/patient/appointment/cancel/{appointment_id}")
	//@CachePut(key="#id",value=HashKey)
	public ResponseEntity<BaseResponse> cancelAppointment(@PathVariable Long appointment_id) {
		try {
			Appointment apt=appointmentService.cancelAppointment(appointment_id);
			BaseResponse b_response=buildResponse("Success","Appointment cancelled successfully","200",apt);
			return new ResponseEntity(b_response,HttpStatus.OK);
		}catch(Exception e) {
			//return (ResponseEntity<Appointment>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Appointment());
			BaseResponse b_response=buildResponse("Failed","Appointment not found","404",null);
			return new ResponseEntity(b_response,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/appointment/{appointment_id}")
	//@CacheEvict(key="#id",value=HashKey)
	public ResponseEntity<BaseResponse> deleteDepartment(@PathVariable Long appointment_id) {
		try {
			appointmentService.deleteAppointment(appointment_id);
			BaseResponse b_response=buildResponse("Success","Appointment deleted successfully","200",null);
			return new ResponseEntity(b_response,HttpStatus.OK);
		}catch(Exception e) {
			BaseResponse b_response=buildResponse("Failed","Appointment record not found","404",null);
			return new ResponseEntity(b_response,HttpStatus.NOT_FOUND);
		}
	}
	
}
