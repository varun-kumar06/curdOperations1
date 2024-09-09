package com.sample.OwnLearning;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="car_table")
public class Car {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int RegNo;
	private String brand;
	private String model;
	private int price;
	private String engineCC;
	private boolean isPetrol;
	
	
	public int getRegNo() {
		return RegNo;
	}
	public void setRegNo(int regNo) {
		RegNo = regNo;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getEngineCC() {
		return engineCC;
	}
	public void setEngineCC(String engineCC) {
		this.engineCC = engineCC;
	}
	public boolean isPetrol() {
		return isPetrol;
	}
	public void setPetrol(boolean isPetrol) {
		this.isPetrol = isPetrol;
	}
	
	
}
