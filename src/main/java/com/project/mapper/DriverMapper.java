package com.project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.project.dto.DriverDTO;
import com.project.entity.Driver;

@Component
public class DriverMapper {

	/*
	 * ENTITY → DTO
	 */
	public DriverDTO entityToDto(Driver driver) {
		if (driver == null) {
			return null;
		}
		DriverDTO dto = new DriverDTO();
		BeanUtils.copyProperties(driver, dto);
		return dto;
	}

	/*
	 * DTO → ENTITY
	 */
	public Driver dtoToEntity(DriverDTO dto) {
		if (dto == null) {
			return null;
		}
		Driver driver = new Driver();
		BeanUtils.copyProperties(dto, driver);
		return driver;
	}

	/*
	 * LIST<ENTITY> → LIST<DTO>
	 */
	public List<DriverDTO> entityListToDtoList(List<Driver> drivers) {
		if (drivers == null || drivers.isEmpty()) {
			return List.of();
		}
		return drivers.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	/*
	 * LIST<DTO> → LIST<ENTITY>
	 */
	public List<Driver> dtoListToEntityList(List<DriverDTO> dtos) {
		if (dtos == null || dtos.isEmpty()) {
			return List.of();
		}
		return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}

}
