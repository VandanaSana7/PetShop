
	
	package com.petshop.in.service;

	import java.util.List;
	import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.petshop.in.enums.transaction_status;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customer.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customer.CustomerFirstnameLastnameNotFoundException;
import com.petshop.in.exceptions.customer.CustomerStateNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionCustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionStatusNotFoundException;
import com.petshop.in.exceptions.customer.CustomersCannotBeAddedException;
import com.petshop.in.model.Customers;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Suppliers;
import com.petshop.in.model.Transactions;

	public interface CustomerService {

		List<Customers> retreiveAllCustomers();

		

		List<Customers> getCustomerByfirstNameAndLastName(String firstName,String lastName) throws CustomerFirstnameLastnameNotFoundException;

	    List<Customers> getCustomerBycity(String city) throws CustomerCityNotFoundException;

		
		List<Customers> getCustomerBystate(String state)throws CustomerStateNotFoundException;

		Customers getCustomerById(int customer_id) throws CustomerIdNotFoundException ;

		SuccessResponse addCustomers(Customers customers) throws CustomersCannotBeAddedException;

		SuccessResponse updateCustomerById(int customerId,Customers updatedCustomers) throws CustomerIdNotFoundException, CustomerCannotBeUpdatedException;

		List<Transactions> getCustomersByTransactionStatus(@PathVariable transaction_status status) throws CustomerTransactionStatusNotFoundException;
		
		List<Customers> findCustomersByNotransaction();
		
		Optional<Pets> getAllPetsByCustomerId(int customer_id) throws CustomerIdNotFoundException;

		List<Transactions> findCustomersByTransactionStatus(transaction_status status)
				throws CustomerTransactionStatusNotFoundException;

		List<Transactions> getCustomerBytranscustid(int customer_id)
				throws CustomerTransactionCustomerIdNotFoundException;
		

	}



