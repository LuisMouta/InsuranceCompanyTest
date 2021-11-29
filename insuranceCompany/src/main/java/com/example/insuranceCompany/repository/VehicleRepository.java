package com.example.insuranceCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insuranceCompany.model.Vehicle;
//first parameter is the type of the entity, second one type of primary key ID
//No need to add @Repository because JPA internally provides @Repository Annotation
//No need to add @Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
