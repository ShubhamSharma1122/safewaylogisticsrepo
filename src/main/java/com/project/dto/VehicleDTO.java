package com.project.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehicleDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;

	@NotBlank(message = "Vehicle name is required")
	@Size(min = 2, max = 50, message = "Vehicle name must be between 2 and 50 characters")
	private String vehicleName;

	@NotBlank(message = "Vehicle number is required")
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$", message = "Invalid vehicle number format (e.g. MH12AB1234)")
	private String vehicleNumber;

	@NotNull(message = "Driver ID is required")
	private Long driverId;
}
