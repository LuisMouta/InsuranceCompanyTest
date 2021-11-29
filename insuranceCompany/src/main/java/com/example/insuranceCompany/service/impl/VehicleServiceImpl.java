package com.example.insuranceCompany.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.insuranceCompany.exception.ResourceNotFoundException;
import com.example.insuranceCompany.model.FuelType;
import com.example.insuranceCompany.model.Vehicle;
import com.example.insuranceCompany.repository.VehicleRepository;
import com.example.insuranceCompany.service.VehicleService;

/*
 * Inject dependency right
 *  Setter-based dependency injection (optional parameters)
 *  Constructor-based dependency injection (mandatory parameters)
 * 
 */

@Service
public class VehicleServiceImpl implements VehicleService {

	private VehicleRepository vehicleRepository;
	
	
	@Autowired
	//inject all the necessary dependencies
	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
	}


	@Override
	public Vehicle saveVehicle(Vehicle vehicle) throws IOException {
		/*missing properties -> number of Doors, fuelType, Power */
		vehicle.setNumberDoors((int) (2 + Math.random() * 5));
		
		vehicle.setFuelType(generateRandomFuelType());
		
		vehicle.setPower((int) (30 + Math.random() * 100));

		BufferedReader br = new BufferedReader(new FileReader("vehicleInfo.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    System.out.println(everything);
		} finally {
		    br.close();
		}
		
		
		return vehicleRepository.save(vehicle);
	}

	
	public FuelType generateRandomFuelType() {
		List<FuelType> VALUES = Collections.unmodifiableList(Arrays.asList(FuelType.values()));
		int size = VALUES.size();
		Random random = new Random();
		return VALUES.get(random.nextInt(size));
	}

	@Override
	public List<Vehicle> getAllVehicleS() {
		List<Vehicle> allVehicles = vehicleRepository.findAll();
		for (Vehicle vehicle : allVehicles) {
			Locale l = new Locale("", vehicle.getCountryOfLicense());
			vehicle.setCountryOfLicense(l.getDisplayCountry());
		}
		return allVehicles;
	}


	@Override
	public Vehicle getVehicleByPlate(int plate) {
		/*
		Optional<Vehicle> vehicle = vehicleRepository.findById(plate);
		if(vehicle.isPresent()) {
			return vehicle.get();
		}else {
			throw new ResourceNotFoundException("Vehicle","ID", plate);
		}	
		*/
		return vehicleRepository.findById(plate).orElseThrow(() -> new ResourceNotFoundException("Vehicle","ID", plate));
	}


	@Override
	public Vehicle updateVehicle(Vehicle vehicle, int plate) {
		
		//checking if a vehicle with a given ID exists in DB or not
		Vehicle existingVehicle = vehicleRepository.findById(plate).orElseThrow(() -> new ResourceNotFoundException("Vehicle","ID", plate));
		
		existingVehicle.setCountryOfLicense(vehicle.getCountryOfLicense());
		existingVehicle.setFuelType(vehicle.getFuelType());
		existingVehicle.setNumberDoors(vehicle.getNumberDoors());
		existingVehicle.setPower(vehicle.getPower());
		existingVehicle.setRiskFactor(vehicle.getRiskFactor());
		
		vehicleRepository.save(existingVehicle);
		
		return existingVehicle;
	}
}
