package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends CommonModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3570146593849378564L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="product_Name",nullable =false)
	private String  productName;
	@Column(name="product_Quntity",nullable = false)
	private Integer productQuantity;
	@Column(name="product_price",nullable = false)
	private Integer  productPrice;
	
	
	
}
