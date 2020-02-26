package com.apap.tu04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.repository.FlightDB;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDB;

	@Override
	public void addFlight(FlightModel flight) {
		flightDB.save(flight);
	}

	@Override
	public void deleteFlight(FlightModel flightModel) {
        flightDB.delete(flightModel);
		
	}

	@Override
	public FlightModel getById(Long id) {
		// TODO Auto-generated method stub
		return flightDB.findById(id).get();	
		}
}
