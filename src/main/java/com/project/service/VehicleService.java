package com.project.service;

import org.springframework.data.domain.Page;

import com.project.dto.VehicleDTO;
import com.project.entity.Vehicle;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;

import jakarta.validation.Valid;

public interface VehicleService {

	String createVehicle(VehicleDTO vehicleDTO) throws NotFoundException;

	Page<Vehicle> filterVehicle(int pageIndex, Integer pageSize, VehicleDTO dto);

	String updateVehicle(@Valid VehicleDTO dto) throws NotFoundException;

	Vehicle getVehicle(Long vehicleId) throws NotFoundException;

	void changeStatus(Long vehicleId, Boolean active) throws ValidationException, NotFoundException;

}
