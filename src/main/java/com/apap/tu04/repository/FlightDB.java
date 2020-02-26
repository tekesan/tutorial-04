package com.apap.tu04.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tu04.model.FlightModel;

public interface FlightDB extends JpaRepository<FlightModel, Long>{

}
