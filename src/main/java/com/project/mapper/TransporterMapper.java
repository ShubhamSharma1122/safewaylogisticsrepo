package com.project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.project.dto.TransportersDTO;
import com.project.entity.Transporters;

@Component
public class TransporterMapper {

	/*
	 * dto to entite
	 */
	public Transporters dtoToEntity(TransportersDTO dto) {
		Transporters entity = new Transporters();

		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	/*
	 * entite to dto
	 */
	public TransportersDTO entitToDot(Transporters enti) {
		TransportersDTO dto = new TransportersDTO();
		BeanUtils.copyProperties(enti, dto);
		return dto;
	}

	/*
	 * converting list of transporters into transports dto
	 */


	public List<TransportersDTO> entitesToDtos(List<Transporters> transports) {
		return transports.stream().map(this::entitToDot).collect(Collectors.toList());
	}

}
