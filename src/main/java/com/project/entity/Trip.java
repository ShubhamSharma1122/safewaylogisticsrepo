package com.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Trip  extends  CommonModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4766603951063027170L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="source",nullable = false)
	private String source;
	@Column(name="starting-date",nullable = false)
	private LocalDateTime startingDate;
	@Column(name="destination",nullable = false)
	private String destination;
	@Column(name="destiona-date",nullable = false)
	private LocalDateTime destinationDate;
	
	
	
	
	
}
