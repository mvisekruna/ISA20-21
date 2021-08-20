package com.back.apoteka.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="work_time")
public class WorkTime {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "fromTime")
	private Date from;

	@Column(name = "toTime")
	private Date to;
	
	@Column(name = "day")
	private Long day;
	
	@ManyToOne
	private User pharmacist;
	
	@ManyToOne
	private User dermatologist;
	
	
	

}
