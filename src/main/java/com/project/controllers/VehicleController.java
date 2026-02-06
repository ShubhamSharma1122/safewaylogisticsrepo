package com.project.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.VehicleDTO;
import com.project.entity.Vehicle;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.locale.MessageByLocaleService;
import com.project.mapper.VehicleMapper;
import com.project.responseHandler.GenericResponseHandlers;
import com.project.service.VehicleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("vehicle")
@Slf4j
public class VehicleController {
	private final VehicleService vehicleService;
	private final VehicleMapper vehicleMapper;
	private final MessageByLocaleService messageSource;

	@PostMapping("/create")
	public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) throws NotFoundException {
		log.info("inside  create  vehicle  method");
		String message = vehicleService.createVehicle(vehicleDTO);
		return new GenericResponseHandlers.Builder().setMessage(message).setStatus(HttpStatus.OK).create();
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateVehicle(@Valid @RequestBody VehicleDTO dto) throws NotFoundException {
		log.info("inside  update vehicleer");
		String respone = vehicleService.updateVehicle(dto);
		return new GenericResponseHandlers.Builder().setMessage(respone).setStatus(HttpStatus.OK).create();
	}

	@GetMapping("byid/{vehicleId}")
	public ResponseEntity<?> getSingleVehicle(@PathVariable Long VehicleId) throws NotFoundException {
		log.info("inside get a single vehicle");
		Vehicle vehicle = vehicleService.getVehicle(VehicleId);
		return new GenericResponseHandlers.Builder().setData(vehicle)
				.setMessage(messageSource.getMessage("vehicle.id.fetched", null)).setStatus(HttpStatus.OK).create();

	}

	@PostMapping("/list")
	public ResponseEntity<Object> getList(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "20") Integer pageSize, @RequestBody VehicleDTO dto) {

		log.info("inside filtr  vehicle  ");

		int pageIndex = pageNumber - 1;

		Page<Vehicle> pages = vehicleService.filterVehicle(pageIndex, pageSize, dto);

		return new GenericResponseHandlers.Builder().setData(vehicleMapper.listofVehicletoListofDto(pages.getContent()))
				.setPageNumber(pageNumber).setPageSize((long) pages.getSize()).setTotalPages(pages.getTotalPages())
				.setTotalCount(pages.getTotalElements()).setHasNextPage(pages.hasNext())
				.setHasPreviousPage(pages.hasPrevious())
				.setMessage(messageSource.getMessage("fetched.list.of.vehcile", null)).setStatus(HttpStatus.OK)
				.create();
	}

	@PostMapping("/status/{vehicleId}/{active}")
	public ResponseEntity<Object> changeStatus(@PathVariable Long vehicleId, Boolean active)
			throws ValidationException, NotFoundException {
		log.info("inside vehicle status controller");

		vehicleService.changeStatus(vehicleId, active);
		log.info("outside the update vehicle controller");
		return new GenericResponseHandlers.Builder().setMessage(messageSource.getMessage("vehicle.status", null))
				.setStatus(HttpStatus.OK).create();
	}

}
