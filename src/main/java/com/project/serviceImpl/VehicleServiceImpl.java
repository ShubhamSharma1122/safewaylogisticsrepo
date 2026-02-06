package com.project.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.project.dto.VehicleDTO;
import com.project.entity.Driver;
import com.project.entity.Vehicle;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.locale.MessageByLocaleService;
import com.project.mapper.VehicleMapper;
import com.project.repo.DriverRepository;
import com.project.repo.VehicleRepo;
import com.project.service.VehicleService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {


	private final VehicleRepo vehicleRepository;
	private final VehicleMapper vehicleMapper;
	private final DriverRepository driverRepository;
	private final MessageByLocaleService messsageSource;


	@Override
	public String createVehicle(VehicleDTO vehicleDTO) throws NotFoundException {
		log.info("inside create vehicle service");
		Long driverID = vehicleDTO.getDriverId();
		Driver driver = driverRepository.findById(driverID)
				.orElseThrow(() -> new NotFoundException(messsageSource.getMessage("driver.not.found", null)));
		Vehicle vehicle = new Vehicle();
		vehicle.setDriver(driver);
		BeanUtils.copyProperties(vehicleDTO, vehicle);
		vehicleRepository.save(vehicle);

		return messsageSource.getMessage("vehicle.save", null);
	}

	@Override
	public Page<Vehicle> filterVehicle(int pageIndex, Integer pageSize, VehicleDTO dto) {
		log.info("inside  filter  vehilce");
		return null;
	}

	@Override
	@Transactional
	public void changeStatus(Long vehicleId, Boolean active) throws ValidationException, NotFoundException {

		log.info("Change Vehicle Status | vehicleId={}, requestedStatus={}", vehicleId, active);


		if (active == null) {
			throw new ValidationException(messsageSource.getMessage("active.not.null", null));
		}

		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new NotFoundException(messsageSource.getMessage("vehicle.not.found", null)));


		if (vehicle.getActive().equals(active)) {
			throw new ValidationException(
					messsageSource.getMessage(active ? "vehicle.already.active" : "vehicle.already.inactive", null));
		}


		if (Boolean.TRUE.equals(active)) {
			Driver driver = vehicle.getDriver();

			if (driver == null) {
				throw new ValidationException(messsageSource.getMessage("vehicle.driver.not.assigned", null));
			}

			if (Boolean.FALSE.equals(driver.getActive())) {
				throw new ValidationException(messsageSource.getMessage("driver.inactive", null));
			}
		}


		vehicle.setActive(active);
		vehicleRepository.save(vehicle);

		log.info("Vehicle status updated successfully | vehicleId={}, active={}", vehicleId, active);
	}

	@Override
	public String updateVehicle(@Valid VehicleDTO dto) throws NotFoundException {
		log.info("inside update vehicle ");
		if (dto.getId() != null) {
			Vehicle vehicle = vehicleRepository.findById(dto.getId())
					.orElseThrow(() -> new NotFoundException(messsageSource.getMessage("vehicle.not.found", null)));
			vehicle.setActive(true);

			Driver driver = driverRepository.findById(dto.getDriverId())
					.orElseThrow(() -> new NotFoundException(messsageSource.getMessage("driver.not.found", null)));
			vehicle.setDriver(driver);
			vehicle.setVehicleName(dto.getVehicleName());
			vehicle.setVehicleNumber(dto.getVehicleNumber());
			vehicleRepository.save(vehicle);
		}
		return messsageSource.getMessage("vehicle.updated", null);
	}

	@Override
	public Vehicle getVehicle(Long vehicleId) throws NotFoundException {
		log.info("inside find vehicle by its id", vehicleId);
		return vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new NotFoundException(messsageSource.getMessage("vehicle.not.found", null)));
	}

}
