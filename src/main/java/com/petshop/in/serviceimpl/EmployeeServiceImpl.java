package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.Employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeeNameNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeeNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeePositionNotFoundException;
import com.petshop.in.model.Employees;
import com.petshop.in.repository.EmployeesRepository;
import com.petshop.in.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeesRepository employeerepos;
	public EmployeeServiceImpl(EmployeesRepository emprepos) {
		this.employeerepos = emprepos;
	}
	@Override
	public List<Employees> getAllEmployees() throws EmployeeNotFoundException{
		List<Employees> emp=employeerepos.findAll();
		if(emp.isEmpty()) {
			throw new EmployeeNotFoundException("Validation Failed:Employees not found");
		}
		return emp;
		
	}
	@Override
	public Employees getEmployeesById(int employee_id) throws EmployeeIdNotFoundException {
//		System.out.println("aaaa");
//		if(Validationclass.ValidationString(employee_id)) {
//			System.out.println("aaaa");
//			throw new EmployeeIdNotFoundException("Validation Failed:Id should be of type int");
//		}
		Employees emp=employeerepos.findById(employee_id);
		
		if(emp==null) {
			System.out.println("id not found");
			throw new EmployeeIdNotFoundException("Validation Failed:Id not found");
		}
		
		return emp;
			
		
		
	}
	@Override
	public Employees getEmployeesByFirstName(String firstName) {
		// TODO Auto-generated method stub
		Employees emp=employeerepos.findByFirstName(firstName);
		if(emp==null) {
			throw new EmployeeNameNotFoundException("Validation Failed");
		}
		
		return emp;
	}
	@Override
	public Optional<Employees> getEmployeesByLastName(String lastName) {
		// TODO Auto-generated method stub
		return employeerepos.findByLastName(lastName);
	}
	@Override
	public Employees getEmployeesByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		Employees emp=employeerepos.findByFirstNameAndLastName(firstName,lastName);
		if(emp==null) {
			throw new EmployeeNameNotFoundException("Validation Failed:Name not found");
		}
		
		return emp;
	}

	public List<Employees> getEmployeesByPosition(String position) {
		// TODO Auto-generated method stub
		List<Employees> emp=employeerepos.findByPosition(position);
		if(emp.isEmpty()) {
			throw new EmployeePositionNotFoundException("Validation Failed:Position not found");
		}
		return emp;
	}
	@Override
	public SuccessResponse addEmployee(Employees emp) throws EmployeeCannotBeAddedException {
		
			Employees e=employeerepos.save(emp);
			if(!Validationclass.ValidationInt(e.getFirstName())){
				throw new EmployeeCannotBeAddedException("Validation failed:First name should be string");
			}
			if(!Validationclass.ValidationInt(e.getLastName())){
				throw new EmployeeCannotBeAddedException("Validation failed:Last name should be string");
			}
			if(!Validationclass.ValidationInt(e.getPosition())){
				throw new EmployeeCannotBeAddedException("Validation failed:Position should be string");
			}
			if(!Validationclass.ValidationInt(e.getPhoneNumber())){
				throw new EmployeeCannotBeAddedException("Validation failed:Phone number should be int");
			}
			if(!Validationclass.ValidationDate(e.getHireDate().toString())){
				throw new EmployeeCannotBeAddedException("Validation failed:Date is not valid");
			}
			
			SuccessResponse s = new SuccessResponse();
			s.setTimestamp(LocalDate.now());
			s.setMessage("Data added");
			s.setStatus("Success");
			return s;
		
	}
	@Override
	public SuccessResponse updateEmployee(int employee_id,Employees employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException {
		// TODO Auto-generated method stub
		Employees emp=employeerepos.findById(employee_id);
		if(emp==null) {
			throw new EmployeeIdNotFoundException("Validation Failed:Id not found");
		}
		if(!Validationclass.ValidationInt(employee.getFirstName())){
			throw new EmployeeCannotBeUpdatedException("Validation failed:First name should be string");
		}
		if(!Validationclass.ValidationInt(employee.getLastName())){
			throw new EmployeeCannotBeUpdatedException("Validation failed:Last name should be string");
		}
		if(!Validationclass.ValidationInt(employee.getPosition())){
			throw new EmployeeCannotBeUpdatedException("Validation failed:Position should be string");
		}
		if(Validationclass.ValidationInt(employee.getPhoneNumber())){
			throw new EmployeeCannotBeUpdatedException("Validation failed:Phone number should be int");
		}
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setPhoneNumber(employee.getPhoneNumber());
		emp.setPosition(employee.getPosition());
		emp.setEmail(employee.getEmail());
		emp.setHireDate(employee.getHireDate());
		emp.setAddress(employee.getAddress());
		
		SuccessResponse s = new SuccessResponse();
		s.setTimestamp(LocalDate.now());
		s.setMessage("Data updated");
		s.setStatus("Success");
	
		return s;
	}
	
	

}

