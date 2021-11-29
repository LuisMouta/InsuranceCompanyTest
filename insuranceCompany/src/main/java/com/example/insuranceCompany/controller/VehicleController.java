package com.example.insuranceCompany.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.insuranceCompany.model.Vehicle;
import com.example.insuranceCompany.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	private VehicleService vehicleService;
	
	public VehicleController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}
	
	//build create vehicle REST API
	//@RequestBody annotation allows us to retrieve the request's body and automatically convert it to Java Object
	@PostMapping()
	public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle) throws IOException{
		return new ResponseEntity<Vehicle>(vehicleService.saveVehicle(vehicle), HttpStatus.CREATED);
		
	}
	
	//build get all vehicle REST API
	@GetMapping
	public List<Vehicle> getAllVehicles(){
		return vehicleService.getAllVehicleS();
	}
	
	//http://localhost:8080/api/vehicles/*id*
	@GetMapping("{license}")
	public ResponseEntity<Vehicle> getVehicleByPlate(@PathVariable("license") int plate){
		return new ResponseEntity<Vehicle>(vehicleService.getVehicleByPlate(plate),HttpStatus.OK);
	}
	
	//build update vehicle REST API
	//http://localhost:8080/api/vehicles/*id*
	@PutMapping("{license}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable("license") int plate, @RequestBody Vehicle vehicle){
		return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicle,plate),HttpStatus.OK);
		
	}
}
