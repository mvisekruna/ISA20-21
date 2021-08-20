package com.back.apoteka.request;

import lombok.Data;
//koristi se i za dodavanje dermatologa i farmaceuta u apoteku
@Data
public class AddPhamracyAdminReuest {

	private String adminEmail;
	private Long pharmacyId;
}
