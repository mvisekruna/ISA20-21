package com.back.apoteka.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "absence")
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date startAbsence;
	
	private Date endAbsence;
	
	@ManyToOne
	private User pharmacist;
	
	@ManyToOne
	private User dermatologist;
	
	private boolean approved; // da li je dozvoljen godisnji

}
