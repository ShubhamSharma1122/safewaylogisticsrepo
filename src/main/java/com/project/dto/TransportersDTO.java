package com.project.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TransportersDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;

	@NotBlank(message = "Owner name is required")
	@Size(min = 2, max = 50, message = "Owner name must be between 2 and 50 characters")
	private String ownerName;

	@NotBlank(message = "Transport name is required")
	@Size(min = 2, max = 100, message = "Transport name must be between 2 and 100 characters")
	private String transportName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotNull(message = "Contact number is required")
	@Digits(integer = 10, fraction = 0, message = "Contact number must be 10 digits")
	private Long contactNumber;

	@NotBlank(message = "GST number is required")
	@Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$", message = "Invalid GST number format")
	private String gstNo;

	@NotBlank(message = "Address is required")
	@Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
	private String address;

}
