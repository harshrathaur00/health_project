package com.example.health.controller;

import com.example.health.models.Hospital;
import com.example.health.service.HospitalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;
import java.util.List;

@RestController
public class HospitalController
{
    @Autowired
    private HospitalService hospitalService;

    Logger logger = LogManager.getLogger(HospitalController.class);

    @GetMapping("/hospital")
    public List<Hospital> getAllList()
    {
        List<Hospital> list =  this.hospitalService.getAllHosp();
        logger.info("Fetched all records of hospitals from db!");
        return list;
    }
    @GetMapping("/hospital/{id}")
    public Hospital getHos(@PathVariable String id)
    {
        return this.hospitalService.getHospById(id);
    }

    @PostMapping("/hospital")
    public ResponseEntity<String> addHosp(@RequestBody Hospital hospital)
    {
        hospitalService.addNewHosp(hospital);
        return  new ResponseEntity<>("hospital added successfully", HttpStatus.OK);
    }


    @PutMapping("/hospital/{id}")
    public void updateHosp(@PathVariable("id") String id, @RequestBody Hospital hospital)
    {
        hospitalService.updateHos(id,hospital);

    }

    @DeleteMapping("/hospital/{id}")
    public void deleHos(@PathVariable("id") String id)
    {
        logger.info("Deleted record successfully");
        this.hospitalService.deleteHosp(id);
    }
}
