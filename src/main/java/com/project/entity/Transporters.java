package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Transporters extends CommonModel {

	private static final long serialVersionUID = 8937784807240619330L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "owner_name", nullable = false)
	private String ownerName;

	@Column(name = "transport_name", nullable = false)
	private String transportName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "contact_no", nullable = false)
	private Long contactNumber;

	@Column(name = "gst_no", nullable = false)
	private String gstNo;

	@Column(name = "address", nullable = false)
	private String address;

//	@OneToMany(mappedBy = "transport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Driver> drivers;
}
