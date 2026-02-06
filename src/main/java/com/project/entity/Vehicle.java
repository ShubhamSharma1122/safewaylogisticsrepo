package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Vehicle extends CommonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6061297756281962766L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="vehicle_name",nullable = false)
	private String  vehicleName;
	@Column(name="vehicle_number")
	private String  vehicleNumber;
	@OneToOne
	@JoinColumn
	private Driver driver;
    
}
