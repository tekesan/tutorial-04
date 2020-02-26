package com.apap.tu04.service;

import com.apap.tu04.model.PilotModel;

public interface PilotService {
PilotModel getPilotDetailByLicenseNumber(String licensenumber);
void addPilot(PilotModel pilot);
PilotModel getPilotDetailByID(Long id);
void delete(PilotModel pilot);
}
