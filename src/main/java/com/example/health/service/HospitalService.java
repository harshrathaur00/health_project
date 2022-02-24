package com.example.health.service;

import com.example.health.models.Hospital;
import com.example.health.repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HospitalService
{
    @Autowired
    private HospitalRepo hospitalRepo;
    List<Hospital> list = null;

    public List<Hospital> getAllHosp()
    {
       list =  this.hospitalRepo.findAll();
       return  list;
    }

    public void addNewHosp(Hospital hospital)
    {
        Optional<Hospital> op = hospitalRepo.findHospitalByUsername(hospital.getUsername());
        if(op.isPresent())
            throw new IllegalStateException("username already taken");
        hospitalRepo.save(hospital);
    }

    public ResponseEntity<String> deleteHosp(String id)
    {

        boolean exist = hospitalRepo.existsById(id);
        if(!exist)
        {
            return new ResponseEntity<>("Not present",HttpStatus.BAD_REQUEST);
        }
        hospitalRepo.deleteById(id);
        hospitalRepo.delete(hospitalRepo.getById(id));
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    public void updateHos(String id, Hospital hospital)
    {
        Hospital h = hospitalRepo.findById(id).get();
        h.setName(hospital.getName());
        if(Objects.nonNull(hospital.getName()) &&!"".equalsIgnoreCase(hospital.getName()))
            h.setName(hospital.getName());
        if(Objects.nonNull(hospital.getEmail()) &&!"".equalsIgnoreCase(hospital.getEmail()))
            h.setEmail(hospital.getEmail());
        if(Objects.nonNull(hospital.getAddress()) &&!"".equalsIgnoreCase(hospital.getAddress()))
            h.setAddress(hospital.getAddress());
        if(Objects.nonNull(hospital.getCity()) &&!"".equalsIgnoreCase(hospital.getCity()))
            h.setCity(hospital.getCity());
        h.setPincode(hospital.getPincode());
        hospitalRepo.save(h);



    }

    public Hospital getHospById(String id)
    {

         return hospitalRepo.findById(id).get();
    }
}

