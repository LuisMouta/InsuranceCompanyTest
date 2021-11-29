package com.example.insuranceCompany.service;

import java.io.IOException;
import java.util.List;

import com.example.insuranceCompany.model.Vehicle;

public interface VehicleService {
	Vehicle saveVehicle(Vehicle vehicle) throws IOException;
	List<Vehicle> getAllVehicleS();
	Vehicle getVehicleByPlate(int plate);
	Vehicle updateVehicle(Vehicle vehicle,int plate);
}
