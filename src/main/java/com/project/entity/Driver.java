package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Driver extends CommonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5973156649789924329L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "driver_name", nullable = false)
	private String driverName;

	@Column(name = "driver_contact", nullable = false)
	private Long driverContact;

	@ManyToOne
	@JoinColumn(name = "transporter_id", nullable = false)
	private Transporters transport;

}
