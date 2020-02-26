package com.apap.tu04.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tu04.model.PilotModel;

public interface PilotDB extends JpaRepository<PilotModel, Long> {
	PilotModel findByLicenseNumber(String licensenumber);
	
}
