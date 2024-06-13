package com.petshop.in.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.Employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeeNotFoundException;
import com.petshop.in.model.Employees;
import com.petshop.in.serviceimpl.EmployeeServiceImpl;

@RestController
//@RequestMapping("/api/v1/employees")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl emp_service;
	
	@RequestMapping("/api/v1/employees")
	public List<Employees> getEmployeesData() throws EmployeeNotFoundException{
		return emp_service.getAllEmployees();

	}
	
	@RequestMapping("/api/v1/employees/{employee_id}")
	//@GetMapping("/{employee_id}")
	public Employees getEmpById(@PathVariable int employee_id) throws EmployeeIdNotFoundException{
		return emp_service.getEmployeesById(employee_id);

	}
	@RequestMapping("/api/v1/employees/firstname/{firstName}")
	//@GetMapping("/firstname/{firstName}")
	public Employees getEmpByFirstName(@PathVariable String firstName){
		return emp_service.getEmployeesByFirstName(firstName);

	}
	@RequestMapping("/api/v1/employees/lastname/{lastName}")
	//@GetMapping("/lastname/{lastName}")
	public Optional<Employees> getEmpByLastName(@PathVariable String lastName){
		return emp_service.getEmployeesByLastName(lastName);

	}
	
	@RequestMapping("/api/v1/employees/name/{firstName}/{lastName}")
	//@GetMapping("/name/{firstName}/{lastName}")
	public Employees getEmpByName(@PathVariable String firstName,@PathVariable String lastName){
		return emp_service.getEmployeesByName(firstName,lastName);

	}
	
	@RequestMapping("/api/v1/employees/position/{position}")
	//@GetMapping("/position/{position}")
	public List<Employees> getEmpByPosition(@PathVariable String position){
		//if(ValidationClass.Val)
		return emp_service.getEmployeesByPosition(position);

	}
	

	@PostMapping("/api/v1/employees/add")
	public SuccessResponse addEmp(@RequestBody Employees employee) throws EmployeeCannotBeAddedException {
		return emp_service.addEmployee(employee);
	}
	
	@PutMapping("/api/v1/employees/update/{employee_id}")
	public SuccessResponse updateEmp(@PathVariable int employee_id,@RequestBody Employees employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException {
		return emp_service.updateEmployee(employee_id,employee);
	}


	

}
