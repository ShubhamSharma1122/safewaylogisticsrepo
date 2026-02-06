package com.project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.project.dto.VehicleDTO;
import com.project.entity.Vehicle;

@Component
public class VehicleMapper {

	public Vehicle dtotoEntity(VehicleDTO dto) {
		Vehicle vehicle = new Vehicle();

		BeanUtils.copyProperties(dto, vehicle);
		return vehicle;
	}

	/*
	 * entity to dto
	 */
	public VehicleDTO entityToDto(Vehicle vehicle) {
		VehicleDTO dto = new VehicleDTO();
		BeanUtils.copyProperties(vehicle, dto);
		return dto;
	}

	/*
	 * list of vehicledtos into vehicle
	 */
	public List<Vehicle> listofVehicleDtoToListofVehicle(List<VehicleDTO> dtos) {
		return dtos.stream().map(this::dtotoEntity).collect(Collectors.toList());
	}

	/*
	 * list of vehicles into list of Vehicledtos
	 */
	public List<VehicleDTO> listofVehicletoListofDto(List<Vehicle> vehicle) {
		return vehicle.stream().map(this::entityToDto).collect(Collectors.toList());
	}

}
