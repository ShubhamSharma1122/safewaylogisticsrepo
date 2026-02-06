package com.project.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DriverDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Driver name is required")
	@Size(min = 2, max = 50, message = "Driver name must be between 2 and 50 characters")
	private String driverName;


	@NotNull(message = "Transporter ID is required")
	private Long transport;
	@NotNull(message = "driverContact is required")
	private Long driverContact;
}
