package com.example.health.service;

import com.example.health.models.Doctor;
import com.example.health.models.Specialization;
import com.example.health.repo.SpecializationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService
{

    @Autowired
    private SpecializationRepo specializationRepo;

    public List<Specialization> getList()
    {
        return this.specializationRepo.findAll();
    }

    public void saveSpec(Specialization sp)
    {
        this.specializationRepo.save(sp);
    }


    public void updateSpec(String spec_id, Specialization spec) throws Exception {
        Optional<Specialization> specialization = specializationRepo.findById(spec_id);
        if(specialization.isPresent()) {
            specializationRepo.save(spec);
        }
       else throw new Exception("hospital with given id not present.");
    }
}
