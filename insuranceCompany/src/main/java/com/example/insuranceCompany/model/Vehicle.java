package com.example.insuranceCompany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data; 

@Data  /* Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check*/
@Entity
@Table(name="vehicles")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Autowired
	@Column(name = "license", nullable = false)
	private String license;
	
	
	@Autowired
	@Column(name = "countryOfLicense", nullable = false)
	private String countryOfLicense;
	
	@Autowired
	@Column(name = "riskFactor", nullable = false)
	private float riskFactor;
	

	@Column(name = "numberDoors")
	private int numberDoors;
	
	@Column(name = "fuelType")
	private FuelType fuelType;
	
	@Column(name = "power")
	private int power;
	
	public long getId() {
		return id;
	}
	
	
	public String getLicense() {
		return license;
	}
	
	public void setLicense(String license) {
		this.license = license;
	}
	
	public String getCountryOfLicense() {
		return countryOfLicense;
	}
	
	public void setCountryOfLicense(String countryOfLicense) {
		this.countryOfLicense = countryOfLicense;
	}
	
	public Float getRiskFactor() {
		return riskFactor;
	}
	
	public void setRiskFactor(Float riskFactor) {
		this.riskFactor = riskFactor;
	}
	
	public int getNumberDoors() {
		return numberDoors;
	}
	
	public void setNumberDoors(int numberDoors) {
		this.numberDoors = numberDoors;
	}
	
	public FuelType getFuelType() {
		return fuelType;
	}
	
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
}
