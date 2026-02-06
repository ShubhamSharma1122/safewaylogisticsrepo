package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

}
