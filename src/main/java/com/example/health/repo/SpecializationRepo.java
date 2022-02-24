package com.example.health.repo;

import com.example.health.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepo extends JpaRepository<Specialization,String>
{
   //  @Query("select Specialization.name from Specialization inner join Doctor on Specialization.s_id = Doctor.doctor_id")
    // Optional<String > findSpecializationByDoctor_Id(String doctor_id);

}
