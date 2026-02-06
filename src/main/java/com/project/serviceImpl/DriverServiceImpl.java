package com.project.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.dto.DriverDTO;
import com.project.dto.DriverFilterDTO;
import com.project.entity.Driver;
import com.project.entity.Transporters;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.locale.MessageByLocaleService;
import com.project.repo.DriverRepository;
import com.project.repo.TransportersRepo;
import com.project.service.DriverService;
import com.project.specifications.DriverSpecification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {
	private final DriverRepository driverRepository;
	private final TransportersRepo transportersRepository;
	private final MessageByLocaleService messagesource;

	/*
	 * save driver
	 */
	@Override
	public String saveDriver(@Valid DriverDTO dto) throws NotFoundException {
		log.info("inside  save  driver  method");
		if (dto.getTransport() != null) {
			Long transporterId = dto.getTransport();
			Transporters transporter = transportersRepository.findById(transporterId)
					.orElseThrow(() -> new NotFoundException(messagesource.getMessage("transporter.not.found", null)));

			Driver driver = new Driver();
			driver.setTransport(transporter);
			BeanUtils.copyProperties(dto, driver);
			driverRepository.save(driver);
		}
		return messagesource.getMessage("driver.save", null);
	}

	@Override
	public String updateDriver(DriverDTO dto) {

		return null;
	}

	/*
	 * change status
	 */
	@Override
	public void changeStatus(final Long driverId, final Boolean active) throws ValidationException, NotFoundException {
		log.info("driver   service : changeStatus for {}", driverId);
		Driver exisitingDriver = driverRepository.findById(driverId)
				.orElseThrow(() -> new NotFoundException(messagesource.getMessage("driver.not.found", null)));
		log.info("Existing driver details {} ", exisitingDriver);
		if (active == null) {
			throw new ValidationException(messagesource.getMessage("active.not.null", null));
		} else if (exisitingDriver.getActive().equals(active)) {
			throw new ValidationException(messagesource
					.getMessage(Boolean.TRUE.equals(active) ? "country.active" : "country.deactive", null));
		} else {
			exisitingDriver.setActive(active);
			driverRepository.save(exisitingDriver);
		}
	}
	@Override
	public Page<Driver> filterDriver(Integer pageNumber, Integer pageSize, DriverFilterDTO filterdto) {

		log.info("inside get all drivers filter");

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		return driverRepository.findAll(new DriverSpecification(filterdto), pageable);
	}

	@Override
	public Driver findDriverId(Long id) throws NotFoundException {
		log.info("inside fetching single driver");
		return driverRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(messagesource.getMessage("driver.not.found", null)));

	}


}
