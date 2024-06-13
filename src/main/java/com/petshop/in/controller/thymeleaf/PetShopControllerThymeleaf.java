package com.petshop.in.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PetShopControllerThymeleaf {

	@GetMapping("/signup")
	public String showSignUpPage()
	{
		return "Signup";
	}
	@GetMapping("/main")
	public String showMainPage()
	{
		return "MainPage";
	}
	
	@GetMapping("/customershome")
	public String showCustomersHomePage()
	{
		return "CustomersHomePage";
	}
	
	@GetMapping("/suppliershome")
	public String showSuppliersHomePage()
	{
		return "SuppliersHomePage";
	}
	
	@GetMapping("/addresshome")
	public String showAddressHomePage()
	{
		return "AddressHomePage";
	}
	
		@GetMapping("/serviceshome")
		public String showServicesHomePage()
		{
			return "ServicesHomePage";
		}
		@GetMapping("/petshome")
		public String showPetsHomePage()
		{
			return "PetsHomePage";
		}
		@GetMapping("/transactionshome")
		public String showTransactionsHomePage()
		{
			return "TransactionsHomePage";
		}
	
		@GetMapping("/VaccinationHomePage")
		public String showVaccinationHomePage() {
			return "VaccinationHomePage";
		}
		
		@GetMapping("/employeeshome")
		public String showEmployeesHomePage()
		{
			return "EmployeesHomePage";
		}
		
		@GetMapping("/petsFoodHomePage")
		public String showPets1HomePage()
		{
			return "PetsFoodHomePage";
		}
		
		@GetMapping("/petCategoryHomePage")
		public String showPetCategoryHomePage()
		{
			return "PetCategoryHomePage";
		}	
}
