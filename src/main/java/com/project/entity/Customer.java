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

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends CommonModel {

	
	private static final long serialVersionUID = 4734558009559644853L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="customer-name",nullable = false)
	private String customerName;
	@Column(name="customer-Contact",nullable = false)
	private Long customerContact;
	@Column(name="budget",nullable = false)
	private Long budget;
	@ManyToOne()
	@JoinColumn(name="product")
	private  Product product;
	
	
}
