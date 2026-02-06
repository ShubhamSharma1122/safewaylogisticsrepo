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

import com.project.dto.TransportersDTO;
import com.project.dto.TransportersFilterDto;
import com.project.entity.Transporters;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.locale.MessageByLocaleService;
import com.project.mapper.TransporterMapper;
import com.project.responseHandler.GenericResponseHandlers;
import com.project.service.TransportersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/transport")
public class TransporterController {
	private final TransportersService transportersService;
	private final TransporterMapper transporterMapper;
	private final MessageByLocaleService messageByLocaleService;

	/*
	 * create transport
	 */
	@PostMapping()
	public ResponseEntity<Object> createTransport(@Valid @RequestBody TransportersDTO dto) {
		log.info("inside  create  transport controller");
		String message = transportersService.createTransport(dto);
		return new GenericResponseHandlers.Builder().setMessage(message).setStatus(HttpStatus.OK).create();
	}

	/*
	 * update transporter
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateTransporter(@Valid @RequestBody TransportersDTO dto) throws NotFoundException {
		log.info("inside  update  transport  controller");
		String respone = transportersService.updateTransporter(dto);
		return new GenericResponseHandlers.Builder()
				.setMessage(messageByLocaleService.getMessage("transporter.updated", null)).setData(respone)
				.setStatus(HttpStatus.OK).create();
	}

	/*
	 * inside filter transporters
	 */
	@PostMapping("/list")
	public ResponseEntity<Object> getTransporters(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "20") Integer pageSize, @RequestBody TransportersFilterDto dto) {

		log.info("inside filter transporters controller");

		// Spring pageable is 0-based
		int pageIndex = pageNumber - 1;

		Page<Transporters> pages = transportersService.filterTransporter(pageIndex, pageSize, dto);

		return new GenericResponseHandlers.Builder().setData(transporterMapper.entitesToDtos(pages.getContent()))
				.setPageNumber(pageNumber) // client-friendly
				.setPageSize((long) pages.getSize()).setTotalPages(pages.getTotalPages())
				.setTotalCount(pages.getTotalElements()).setHasNextPage(pages.hasNext())
				.setHasPreviousPage(pages.hasPrevious())
				.setMessage(messageByLocaleService.getMessage("get.list", new Object[] { "Transporters" }))
				.setStatus(HttpStatus.OK).create();
	}

	/*
	 * get transport by id
	 */

	@GetMapping("/transporter/{transporterId}")
	public ResponseEntity<Object> getSingleTransporter(@PathVariable final long transporterId)
			throws NotFoundException {
		log.info("inside  get  transport  controller ");
		Transporters transporters = transportersService.getTransporter(transporterId);
		return new GenericResponseHandlers.Builder().setData(transporterMapper.entitToDot(transporters))
				.setMessage(messageByLocaleService.getMessage("transporter.fetched", null)).setStatus(HttpStatus.OK)
				.create();
	}

	/*
	 * change status of the transporters
	 */
	@PostMapping("/status/{transporterId}/{active}")
	public ResponseEntity<Object> changeStatus(@PathVariable final Long transporterId,
			@PathVariable final Boolean active) throws NotFoundException, ValidationException {
		log.info("inside  update  status   of  transporters ", transporterId, active);
		transportersService.changeStatus(transporterId, active);
		log.info("Outside change status of Country ");
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setMessage(messageByLocaleService.getMessage("transporter.status.change", null)).create();
	}

}