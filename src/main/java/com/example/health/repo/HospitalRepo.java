package com.example.health.repo;

import com.example.health.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HospitalRepo extends JpaRepository<Hospital,String>
{

    @Query("SELECT h from Hospital h where h.username = ?1")
    Optional<Hospital> findHospitalByUsername(String username);

}
