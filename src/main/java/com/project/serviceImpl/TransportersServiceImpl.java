package com.project.serviceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.dto.TransportersDTO;
import com.project.dto.TransportersFilterDto;
import com.project.entity.Transporters;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.locale.MessageByLocaleService;
import com.project.mapper.TransporterMapper;
import com.project.repo.TransportersRepo;
import com.project.service.TransportersService;
import com.project.specifications.TransporterSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransportersServiceImpl implements TransportersService {
	private final TransportersRepo transportersRepository;
	private final TransporterMapper transporterMapper;
	private final MessageByLocaleService messageSource;

	@Override
	public String createTransport(TransportersDTO transportersDTo)
	{
		log.info("inside create transport Service", transportersDTo);
		Transporters transporters = transporterMapper.dtoToEntity(transportersDTo);
		transportersRepository.save(transporters);
		return messageSource.getMessage("transporter.created", null);
	}


	@Override
	public String updateTransporter(TransportersDTO dto) throws NotFoundException {
	log.info("inside  update  transport  service");
	

		Transporters tranporter = transportersRepository.findById(dto.getId())
				.orElseThrow(() -> new NotFoundException("no  tranporter  is  their  with this  id " + dto.getId()));

		tranporter.setActive(true);
		tranporter.setAddress(dto.getAddress());
		tranporter.setContactNumber(dto.getContactNumber());
		tranporter.setEmail(dto.getEmail());
		tranporter.setGstNo(dto.getGstNo());
		tranporter.setOwnerName(dto.getOwnerName());
		tranporter.setTransportName(dto.getTransportName());
		transportersRepository.save(tranporter);
		return messageSource.getMessage("transporter.updated", null);

	}

	@Override
	public void changeStatus(final Long transportId, final Boolean active)
			throws ValidationException, NotFoundException {
		log.info("transport service : changeStatus for {}", transportId);
		Transporters exisitingtransporter = transportersRepository.findById(transportId)
				.orElseThrow(() -> new NotFoundException(messageSource.getMessage("transporter.not.found", null)));
		log.info("Existing  transporters details {} ", exisitingtransporter);
		if (active == null) {
			throw new ValidationException(messageSource.getMessage("active.not.null", null));
		} else if (exisitingtransporter.getActive().equals(active)) {
			throw new ValidationException(messageSource
					.getMessage(Boolean.TRUE.equals(active) ? "country.active" : "country.deactive", null));
		} else {
			exisitingtransporter.setActive(active);
			transportersRepository.save(exisitingtransporter);
		}
	}

	@Override
	public Page<Transporters> filterTransporter(Integer pageNumber, Integer pageSize, TransportersFilterDto dto) {

		log.info("inside filter transporters service method");

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		// simple active=true specification
		Specification<Transporters> spec = (root, query, cb) -> cb.isTrue(root.get("active"));

		if (dto != null) {
			spec = spec.and(new TransporterSpecification(dto));
		}

		return transportersRepository.findAll(spec, pageable);
	}


	@Override
	public Transporters getTransporter(long transporterId) throws NotFoundException {
		log.info("inside  get a transporter  ");
		return transportersRepository.findById(transporterId)
				.orElseThrow(() -> new NotFoundException(messageSource.getMessage("transporter.not.found", null)));
	}

}
