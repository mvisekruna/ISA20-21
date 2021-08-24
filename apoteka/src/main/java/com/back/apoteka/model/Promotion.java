package com.back.apoteka.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import lombok.Data;


@Data
@Entity
@Table(name = "promotion")
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date startPromo;
	
	private Date endPromo;
	
	private String description;
	
	@ManyToOne
	private Pharmacy pharm;
}
