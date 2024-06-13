package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.Employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.Employees.EmployeeIdNotFoundException;
import com.petshop.in.model.Employees;
import com.petshop.in.repository.EmployeesRepository;
import com.petshop.in.serviceimpl.EmployeeServiceImpl;



	
	@Controller
	public class EmployeeControllerThymleaf {
		@Autowired
		private EmployeeServiceImpl employeeService;
		@Autowired
		private EmployeesRepository emprepo;
		


		 @GetMapping("/api1/v1/employees")
		    public String getAllEmployees(Model model) {
		        List<Employees> employees = employeeService.getAllEmployees();
		        model.addAttribute("employees", employees);
		        return "AllEmployees";
		    }
		 
		 
		 
		// Form to get id from user
			@GetMapping("/api1/v1/employees/Idform")
			public String showEmployeesIdForm() {
				return "EmployeeIdForm";
			}
		 
		 @GetMapping("/api1/v1/employees/id")
		    public String getEmployeeById(@RequestParam("employeeId") int employeeId, Model model) throws EmployeeIdNotFoundException {
		        Employees employee = employeeService.getEmployeesById(employeeId);
		        model.addAttribute("employee", employee);
		        return "EmployeeById";
		    }
		 
		// form to get name from user
			@GetMapping("/api1/v1/employees/nameform")
			public String showEmployeesNameForm() {
				return "EmployeeNameForm";
			}
		 
		 
		 @GetMapping("/api1/v1/employees/name")
		    public String getEmployeeByName(@RequestParam("firstName") String firstName,
		                                    @RequestParam("lastName") String lastName, Model model) {
		        Employees employee = employeeService.getEmployeesByName(firstName, lastName);
		        model.addAttribute("employee", employee);
		        return "EmployeeByName";
		    }
		 
		 
		 @GetMapping("/api1/v1/employees/positionform")
		    public String showEmployeesPositionForm() {
		        return "EmployeePositionForm"; 
		    }
		
		
		 @GetMapping("/api1/v1/employees/position")
		    public String getEmployeesByPosition(@RequestParam("position") String position, Model model) {
		        List<Employees> employees = employeeService.getEmployeesByPosition(position);
		        model.addAttribute("employees", employees);
		        return "EmployeeByPosition";
		    }
		
		

		
		
		 @GetMapping("/api1/v1/employees/postform")
			public String showEmployeeAddForm() {
				return "PostEmployeeForm";
			}

		 
			@PostMapping("/api1/v1/employees/add")
			public String addEmployee(@ModelAttribute("employee") Employees employee,Model model) throws EmployeeCannotBeAddedException {
					
				SuccessResponse sucres = employeeService.addEmployee(employee);
				model.addAttribute("post_employee", sucres);

				return "PostEmployee";


}
			@GetMapping("/api1/v1/employees/updates")
			public String showdetailsforupdate() {
				return "UpdateEmployeeIdForm";
			}

			@GetMapping("/api1/v1/employees/update")
			public String updateEmployee(@RequestParam("employeeId") Integer employeeId, Model model) throws EmployeeIdNotFoundException {
				Employees employee = employeeService.getEmployeesById(employeeId);
				model.addAttribute("Employee", employee);
				return "UpdateEmployeeForm";
			}

//			@GetMapping("/api1/v1/employees/updatedetails")
//			public String updateEmployeeform( Model model, @RequestParam("employeeId") Integer employeeId,
//					@ModelAttribute("employee") Employees  employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException {
//				SuccessResponse sucres =  employeeService.updateEmployee(employeeId, employee);
//				model.addAttribute("UpdateEmployee", sucres);
//
//				return "UpdateEmployeeDetails";
//
//			}	
	
			@GetMapping("/api1/v1/employees/updateemployeedetails")
			public String updateEmployees(Model model, @RequestParam("employeeId") Integer employeeId, 
					@ModelAttribute("employee") Employees employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException{
				SuccessResponse s = employeeService.updateEmployee(employeeId, employee);
				model.addAttribute("UpdateEmployee", s);
		 
				return "UpdateEmployeeDetails";
		 
			}
	
	
	
	}

