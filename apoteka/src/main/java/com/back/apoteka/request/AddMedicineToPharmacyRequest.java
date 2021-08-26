package com.back.apoteka.request;

import lombok.Data;

@Data
public class AddMedicineToPharmacyRequest {
	
	private Long medicineId;
	//private String name;
	
	//private Integer code;
	
	//private String type;
	
	private Long pharmacyId;

}
