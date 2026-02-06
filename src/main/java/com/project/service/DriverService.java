package com.project.service;

import org.springframework.data.domain.Page;

import com.project.dto.DriverDTO;
import com.project.dto.DriverFilterDTO;
import com.project.entity.Driver;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;

import jakarta.validation.Valid;

public interface DriverService {

	String saveDriver(@Valid DriverDTO dto) throws NotFoundException;

	String updateDriver(DriverDTO dto);



	Page<Driver> filterDriver(Integer pageNumber, Integer pageSize, DriverFilterDTO filterdto);

	Driver findDriverId(Long id) throws NotFoundException;

	void changeStatus(Long driverId, Boolean active) throws ValidationException, NotFoundException;

}
