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

import com.project.constant.Constant;
import com.project.dto.DriverDTO;
import com.project.dto.DriverFilterDTO;
import com.project.entity.Driver;
import com.project.exception.NotFoundException;
import com.project.locale.MessageByLocaleService;
import com.project.mapper.DriverMapper;
import com.project.responseHandler.GenericResponseHandlers;
import com.project.service.DriverService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/driver")
@Slf4j
public class DriverController {

	private final DriverService driverService;
	private final DriverMapper driverMapper;

	private final MessageByLocaleService messageByLocaleService;

	/*
	 * create
	 */
	@PostMapping()
	public ResponseEntity<Object> createDriver(@RequestBody @Valid DriverDTO dto) throws NotFoundException {
		log.info("inside  driver  controller crating  driver");
		String msg = driverService.saveDriver(dto);
		return new GenericResponseHandlers.Builder().setMessage(msg).setStatus(HttpStatus.CREATED).create();

	}
	/*
	 * update
	 */

	@PutMapping()
	public ResponseEntity<Object> updateDriver(@RequestBody DriverDTO dto) {
		log.info("inside  update  driver  controller");
		String msg = driverService.updateDriver(dto);
		return new GenericResponseHandlers.Builder().setMessage(msg).setStatus(HttpStatus.CREATED).create();

	}

	/*
	 * list of drivers
	 */
	@GetMapping("/list")
	public ResponseEntity<Object> getDrivers(@RequestParam(defaultValue = Constant.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(defaultValue = Constant.PAGE_SIZE) Integer pageSize, @RequestBody DriverFilterDTO dto) {
		log.info("inside  filter driver controller");
		Page<Driver> pages = driverService.filterDriver(pageNumber, pageSize, dto);
		log.info("getting  outside  of the  get  drivers  filter  .");
		return new GenericResponseHandlers.Builder().setData(driverMapper.entityListToDtoList(pages.getContent()))
				.setHasNextPage(pages.hasNext()).setHasPreviousPage(pages.hasPrevious()).setPageNumber(pageNumber)
				.setTotalCount(pages.getTotalElements()).setPageSize((long) pageSize)
				.setTotalPages(pages.getTotalPages())
				.setMessage(messageByLocaleService.getMessage("get.list.drivers", new Object[] { "pages" }))
				.setStatus(HttpStatus.OK).create();
	}

	/*
	 * get a single driver
	 */

@GetMapping("/driver/{id}")
public ResponseEntity<Object> getDriver(@PathVariable Long id) throws NotFoundException {
	log.info("inside a get sigle driver");
	Driver driver=driverService.findDriverId(id);
	return new GenericResponseHandlers.Builder()
			.setMessage(messageByLocaleService.getMessage("fetching.single.driver", null))
			.setData(driverMapper.entityToDto(driver)).setStatus(HttpStatus.OK).create();
}

}