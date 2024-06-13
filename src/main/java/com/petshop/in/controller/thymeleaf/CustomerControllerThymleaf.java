package com.petshop.in.controller.thymeleaf;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.enums.transaction_status;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.AddressAndSuppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customer.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customer.CustomerFirstnameLastnameNotFoundException;
import com.petshop.in.exceptions.customer.CustomerStateNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionStatusNotFoundException;
import com.petshop.in.exceptions.customer.CustomersCannotBeAddedException;
import com.petshop.in.model.Customers;
import com.petshop.in.model.Suppliers;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.CustomerRepository;
import com.petshop.in.repository.TransactionRepository;
import com.petshop.in.service.CustomerService;
import com.petshop.in.service.TransactionService;


@Controller
public class CustomerControllerThymleaf {
	@Autowired
	private CustomerService custservice;
	@Autowired
	private CustomerRepository custrepo;
	
	@Autowired
	private TransactionService traservice;
	
	@Autowired
	private TransactionRepository trarepo;
	
	
	@GetMapping("/api1/v1/customers/Idform")
	public String showCustomersIdForm() {
	    return "CustomerByIdForm"; // Return the name of your HTML template file
	}
	
	
	@GetMapping("/api1/v1/customers")
	public String retreiveAllCustomers(Model model) {
		List<Customers> customers =custservice.retreiveAllCustomers();
		model.addAttribute("AllCustomers", customers);
		return "AllCustomers";
	}

	@GetMapping("/api1/v1/customers/{id}")
	public String retrieveCustomerById(@RequestParam("customerId") Integer customerId, Model model) 

		 throws CustomerIdNotFoundException {
			 Customers customer = custservice.getCustomerById(customerId);
		 
		model.addAttribute("CustomerById", customer);
		return "CustomerById";

	}
	
	
	
	@GetMapping("/api1/v1/customers/nameform")
	public String showCustomersNameForm() {
		return "CustomerByNameForm";
	}
	
	
	
	
	@GetMapping("/api1/v1/customers/byname")
	    public String getCustomerByfirstNameAndLastName(@RequestParam("firstName") String firstName,
	                                    @RequestParam("lastName") String lastName, Model model) throws CustomerFirstnameLastnameNotFoundException {
		 List<Customers> customer = custservice.getCustomerByfirstNameAndLastName(firstName,lastName);
	        model.addAttribute("CustomerByName", customer);
	        return "CustomerByName";
	    }
	
	
	
	@GetMapping("/api1/v1/customers/cityform")
	public String showCustomersCityForm() {
		return "CustomerByCityForm";
	}
	
	
	@GetMapping("/api1/v1/customers/by_city/{city}")
	public String getCustomerBycity(@RequestParam("cityName") String cityName, Model model)
			throws CustomerCityNotFoundException {
		List<Customers> customer = custservice.getCustomerBycity(cityName);
		model.addAttribute("CustomerByCity", customer);
		return "CustomerByCity";
	}
	
	
	
	
	@GetMapping("/api1/v1/customers/stateform")
	public String showCustomersStateForm() {
		return "CustomerByStateForm";
	}
	
	
	@GetMapping("/api1/v1/customers/by_state/{state}")
	public String getCustomerBystate(@RequestParam("stateName") String stateName, Model model)
	 throws CustomerStateNotFoundException {
		 List<Customers> customer = custservice.getCustomerBystate(stateName);
		
		model.addAttribute("CustomerByState", customer);
		return "CustomerByState";
	}
	
	
	@GetMapping("/api1/v1/customers/addform")
	public String showCustomersAddForm() {
		return "PostCustomerForm";
	}
	
	
	
	@PostMapping("/api1/v1/customers/add")
	public String addCustomers(@ModelAttribute("customer") Customers customer, Model model)
			throws CustomersCannotBeAddedException {
		SuccessResponse sucres = custservice.addCustomers(customer);
		model.addAttribute("post_customer", sucres);
		return "PostCustomer";

	}
	
	
	
	@GetMapping("/api1/v1/customers/updates")
	public String showdetailsforupdate() {
		return "UpdateCustomerIdForm";
	}

	@GetMapping("/api1/v1/customers/update")
	public String updateAddress(@RequestParam("customerId") Integer customerId, Model model) throws CustomerIdNotFoundException {
		Customers customer = custservice.getCustomerById(customerId);
		model.addAttribute("customerId", customer);
		return "UpdateCustomerForm";
	}

	@GetMapping("/api1/v1/customers/updatedetails")
	public String updateAddressform(@RequestParam("customerId") Integer customerId, Model model,
			@ModelAttribute("updateCustomer") Customers customer) throws MismatchDataTypeException, CustomerIdNotFoundException, CustomerCannotBeUpdatedException {
		SuccessResponse sucres = custservice.updateCustomerById(customerId, customer);
		model.addAttribute("updateCustomer", sucres);

		return "UpdateCustomerDetails";

	}
	
	
	@GetMapping("/api1/v1/transaction/statusform")
	public String showCustomerStatus() {
		return "TransactionStatusForm";
	}
	
	@GetMapping("/api1/v1/transaction/{status}")
		public String getCustomersByTransactionStatus(@RequestParam("status") String status, Model model)
		throws CustomerTransactionStatusNotFoundException{
			List<Transactions> trans =traservice.getCustomersByTransactionStatus();
			model.addAttribute("AllTransactions", trans);
			return "AllCustomersTransactionHistory";
		}
	

	@GetMapping("/api1/v1/customers/notransaction")
	public String getCustomersByNotransaction(Model model) {
		List<Customers> customers =custservice.findCustomersByNotransaction();
		model.addAttribute("Notransactions", customers);
		return "NoTransactions";
	}
}




