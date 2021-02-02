package com.back.apoteka.service;

import com.back.apoteka.model.Complaint;
import com.back.apoteka.request.ComplaintRequest;

public interface ComplaintService {


	Complaint writeComplaintForPharmacy(ComplaintRequest cr);

	Complaint writeComplaintForStaff(ComplaintRequest cr);

}
