package com.sample.OwnLearning;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.Employee;

@RestController
@RequestMapping(value="/Car")
public class CarController {
	
	@Autowired
	CarService cs;
	
	@PostMapping(value="/postCar")
	public String postThisCar(@RequestBody List<Car> c) {
		return cs.postThisCar(c);
	}
	
	@GetMapping(value="/getCar")
	public List<Car> getThisCar(){
		return cs.getThisCar();
	} 
	
	@GetMapping(value="/getCarId/{i}")
	 public Car getRegNo(@PathVariable int i) {
		return cs.getRegNo(i);
	}
	
	@PutMapping(value="/update/{i}")
	public String updateThis(@PathVariable int i, @RequestBody Car c) {
		return cs.updateThis(i,c);
	}
	
	@GetMapping(value="/getCarBrand/{b}")
	public List<Car> getCarBrand(@PathVariable String b){
		return cs.getCarBrand(b);
		
	}
	
	@GetMapping(value="/getSecMax")
	public Stream<Car> getSecMax() {
		return cs.getSecMax();
	}
	
	@GetMapping(value="/getBrandRev") 
		public List<String> getBrandRev() {
			return cs.getBrandRev();
		}
	
	@GetMapping(value="/getCarPrice")
	public List<Object> getCarPrice(){
		return cs.getCarPrice();
	}
	
	@GetMapping(value="/getLastCharacter")
	public List<Character>getLastCharacter(){
		return cs.getLastCharacter();
	}
	
	@GetMapping(value="/getLastCars")
	public List<Object> getLastCars(){
		return cs.getLastCars();
	}
}



@Service
class CarService{
	
	@Autowired
	CarDao cd;
	
	public String postThisCar(@RequestBody List<Car> c ) {
		return cd.postThisCar(c) ;
	}
	
	public List<Car> getThisCar(){
		return cd.getThisCar();
	}
	
	 public Car getRegNo( int i) {
			return cd.getRegNo(i);
		}
	 
	 public String updateThis(int i, Car c) {
		 return cd.updateThis(i,c);
	 }
	 
	 public List<Car> getCarBrand(String b){
		 List<Car> x = cd.getAllCars(b);
		  return x.stream()
	                .filter(car -> b.equalsIgnoreCase(car.getBrand()))
	                .collect(Collectors.toList());
	 }
	 
	 public Stream<Car> getSecMax() {
		List< Car> x = cd.getThisCar();
		 return x.stream().sorted(Comparator.comparingInt(Car::getPrice).reversed()).limit(2).skip(1);
	 }
	 
	 public List<String> getBrandRev() {
		 List<Car> x = cd.getThisCar();
		 List<String>y= x.stream().map(a->a.getBrand()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	     return y;
	 }
	 
	 public List<Object> getCarPrice(){
		 List<Car>x= cd.getThisCar();
		 List<Object> y = x.stream()
                 .filter(car -> car.getPrice() > 1000000 && car.getPrice() < 5000000)
                 .map(car -> car.getBrand() + " " + car.getPrice())
                 .collect(Collectors.toList());		
		 return y;
		 
	 }
	 
	 public List<Character>getLastCharacter(){
		 
		 List<Car>x = cd.getThisCar();
		 
		 List<Character> y = x.stream().map(a->a.getBrand().charAt(a.getBrand().length()-1)).collect(Collectors.toList());
		 return y;
	 }
	 
	 public List<Object>getLastCars(){
		 List<Car>x = cd.getThisCar();
		 
		 List<Object> y = x.stream().skip(x.size()-5).collect(Collectors.toList());
		 return y;
	 }

	
	 
}

@Repository
class CarDao {
	@Autowired
	CarRepository cr;
	
	public String postThisCar(@RequestBody List<Car> c) {
	      cr.saveAll(c);
	      return "Posted";
	}
	
	public List<Car> getThisCar() {
		return cr.findAll();
	}
	
	 public Car getRegNo( int i) {
			return cr.findById(i).get();
		}
	 
	 
	 public String updateThis(int i, Car c) {
		 
		 Car x = cr.findById(i).get();
		 x.setBrand(c.getBrand());
		 x.setModel(c.getModel());
		 x.setEngineCC(c.getEngineCC());
		 x.setPrice(c.getPrice());
		 x.setPetrol(c.isPetrol());
		 
		 cr.save(x);
		 return "Updated Successfully";
	 }
	 
	 public List<Car> getAllCars (String b) {
		return cr.findAll();
		 
	 }
}

interface CarRepository extends JpaRepository<Car, Integer> {
	
	
}