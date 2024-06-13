package com.petshop.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.Employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.Employees.EmployeeNotFoundException;
import com.petshop.in.model.Employees;

public interface EmployeeService {
	public Employees getEmployeesByName(String firstName, String lastName);
	public List<Employees> getEmployeesByPosition(String position);
	public Employees getEmployeesById(int employee_id) throws EmployeeIdNotFoundException;
	public List<Employees> getAllEmployees() throws EmployeeNotFoundException;
	public SuccessResponse addEmployee(Employees emp) throws EmployeeCannotBeAddedException;
	public SuccessResponse updateEmployee(int employee_id,Employees employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException;
	Optional<Employees> getEmployeesByLastName(String lastName);
	Employees getEmployeesByFirstName(String firstName);
}
