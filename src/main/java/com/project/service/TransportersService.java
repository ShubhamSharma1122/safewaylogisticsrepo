package com.project.service;

import org.springframework.data.domain.Page;

import com.project.dto.TransportersDTO;
import com.project.dto.TransportersFilterDto;
import com.project.entity.Transporters;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;

import jakarta.validation.Valid;

public interface TransportersService {

	String createTransport(TransportersDTO transportersDTo);

	String updateTransporter(@Valid TransportersDTO dto) throws NotFoundException;

	void changeStatus(Long id, Boolean active) throws ValidationException, NotFoundException;

	Page<Transporters> filterTransporter(Integer pageNumber, Integer pageSize, TransportersFilterDto dto);

	Transporters getTransporter(long transporterId) throws NotFoundException;

//	Page<Transporters> filterTransporter(Integer pageNumber, Integer pageSize, TransportersFilterDto dto);

}
