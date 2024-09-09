package com.sample;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/Employee")
public class EmployeeController {
	@Autowired  
	EmployeeService es;
   
	//postEmployee to add employee details (Creating an Object)
	@PostMapping(value="/postEmployee")
	public String postThisEmployee(@RequestBody Employee e) {
		return es.postThisEmployeeService(e);
	}
	
	// GetALLEmployee to show all employee details`
	@GetMapping(value ="/getAllEmployee")
	public List<Employee> getThisEmployee(){
		return es.getThisEmployeeService();
	}
	
	
	@PostMapping(value="/postAllEmployee")
	public String postAllEmployee(@RequestBody List<Employee> e) {
		return es.postAllEmployee(e);
	} 
	
	@GetMapping(value="/getEmployeeSalary")
	public List<Employee> getEmployeeSalary() {
        return es.getEmployeeSalary();
    }
	
	@GetMapping(value="/getEmployeeAge")
	public List<Employee> getEmployeeAge(){
		return es.getEmployeeAge();
	}
	
	@GetMapping(value="/getId/{i}")
    public Employee getId(@PathVariable int i) {
		return es.getId(i);
	}
	
	@GetMapping(value="/deleteId/{i}")
	public String deleteId(@PathVariable int i) {
		return es.deleteId(i);
	}
}


@Service
 class EmployeeService {
	
	@Autowired
	EmployeeDao ed;
	
	public String postThisEmployeeService(Employee e) {
		return ed.postThisEmployeeDao(e);
	}
	
	
	public List<Employee> getThisEmployeeService(){
		return ed.getThisEmployeeDao();
	}
	
	public String postAllEmployee( List<Employee> e) {
		return ed.postAllEmployee(e);
	}
	
	public List<Employee> getEmployeeSalary() {
        List<Employee> employees = ed.getEmployeeSalary();

        // Find the maximum salary
        int maxSalary = employees.stream().mapToInt(Employee::getSalary).max().orElse(0);
                                 
        // Filter employees who have the maximum salary
        return employees.stream().filter(emp -> emp.getSalary() == maxSalary).toList();
                
                        
    }
	
	public List<Employee> getEmployeeAge(){
		List<Employee> employee= ed.getEmployeeAge();
		
		return employee.stream().filter(x->x.getAge()>30).toList();
			
	}
	
	
	 public Employee getId(int i) {
			return ed.getId(i);
		}
	 
	 public String deleteId( int i) {
			return ed.deleteId(i);
		}

	
}

@Repository
class EmployeeDao{
	@Autowired
	EmployeeRepository er;
	public String postThisEmployeeDao(Employee e) {
	er.save(e);
	return "Posted SuccessFully";
	}
	
	public List<Employee> getThisEmployeeDao(){
          return er.findAll();
           
	}
	
	public String postAllEmployee( List<Employee> e) {
		er.saveAll(e);
		return "Saved Successfully";
		
	}
	
	public List<Employee> getEmployeeSalary() {
        return er.findAll();
    }
	
	public List<Employee> getEmployeeAge(){
		return er.findAll();
	}
	
	 public Employee getId(int i) {
			return er.findById(i).get();
			 
		}
	 
	 public String deleteId( int i) {
		 er.deleteById(i);
		 return "Deleted Successfully";
		}
	
}

interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	
	
}