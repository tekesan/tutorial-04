package com.apap.tu04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tu04.model.PilotModel;
import com.apap.tu04.repository.PilotDB;

@Service
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDB;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDB.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDB.save(pilot);
	}

	@Override
	public PilotModel getPilotDetailByID(Long id) {
        return pilotDB.findById(id).get();

	}

	@Override
	public void delete(PilotModel pilot) {
        pilotDB.delete(pilot);
		
	}
}
