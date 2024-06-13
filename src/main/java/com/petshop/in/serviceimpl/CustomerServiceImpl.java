package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.in.enums.transaction_status;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customer.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customer.CustomerFirstnameLastnameNotFoundException;
import com.petshop.in.exceptions.customer.CustomerNotFoundException;
import com.petshop.in.exceptions.customer.CustomerStateNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionCustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionStatusNotFoundException;
import com.petshop.in.exceptions.customer.CustomersCannotBeAddedException;
import com.petshop.in.model.Customers;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.CustomerRepository;
import com.petshop.in.repository.PetsRepository;
import com.petshop.in.repository.TransactionRepository;
import com.petshop.in.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customer_repo;

	@Autowired
	TransactionRepository trans_repo;
	
	@Autowired
	PetsRepository pets_repo;
      
	@Override
	public List<Customers> retreiveAllCustomers(){
		try {
			return customer_repo.findAll();
		}
		catch(NoSuchElementException ex)
		{
			throw new CustomerNotFoundException("Customer not found");
		}
	}
	
	@Override
	public Customers getCustomerById(int customer_id) throws CustomerIdNotFoundException {
		try
		{
			Customers customers = customer_repo.findById(customer_id).get();
			return customers;
		}
		catch(NoSuchElementException e)
		{
			throw new CustomerIdNotFoundException("Validation failed: No customer found with this Id "+customer_id);
		}
	}
//	    Customers customerOptional = customer_repo.findById(customer_id).get();
//	    if (customerOptional.isEmpty()) {
//	    	throw new CustomerIdNotFoundException("Id not found");
//	    } 
//	    return customerOptional;
//	}

	
	@Override
	public List<Customers> getCustomerBycity(String city) throws CustomerCityNotFoundException {
	    List<Customers> customerOptional = customer_repo.findByCity(city);
	    if (customerOptional.isEmpty()) {
	    	throw new CustomerCityNotFoundException("City not found");
	    } 
	    return customerOptional;
	}



	//public Optional<Pets> getAllPetsByCustomerId(int customer_id) throws CustomerIdNotFoundException {


	
	@Override
	public List<Customers> getCustomerBystate(String state)throws CustomerStateNotFoundException {
		// TODO Auto-generated method stub
		List<Customers> customerOptional = customer_repo.findByState(state);
		if (customerOptional.isEmpty()) {
			throw new CustomerStateNotFoundException("State not found");
		}
		return customerOptional;
	}

	@Override
	public List<Transactions> getCustomerBytranscustid(int customer_id) throws CustomerTransactionCustomerIdNotFoundException {
	    List<Transactions> customerOptional = trans_repo.findByCustomer_CustomerId(customer_id);
	    if (customerOptional.isEmpty()) {
	    	throw new CustomerTransactionCustomerIdNotFoundException("TransactionId not found");
	    } 
	    return customerOptional;
	}

	
	
//	public List<Customers> findCustomersByTransactionStatus(transaction_status status) {
//        return customer_repo.findCustomersByTransactionStatus(status);
//    }
	
//	public List<Customers> findCustomerByTransactionstatus(transaction_status status) throws CustomerTransactionStatusNotFoundException {
//	    List<Customers> customerOptional = customer_repo.findCustomersByTransactionStatus(status);
//	    if (customerOptional.isEmpty()) {
//	    	throw new CustomerTransactionStatusNotFoundException("Transaction status not found");
//	    } 
//	    return customerOptional;
//	}
	
	@Override
	public List<Transactions> findCustomersByTransactionStatus(transaction_status status) throws CustomerTransactionStatusNotFoundException{
		
			
				List<Transactions> customerOptional = trans_repo.findByTransactionStatus(status);
				
			      return customerOptional;
			
//			else
//				throw new CustomerTransactionStatusNotFoundException("No transaction found");
//			
			
		}
		
	
	
	@Override
	public List<Customers> findCustomersByNotransaction() {
		// TODO Auto-generated method stub
		return customer_repo.findCustomerByNotransaction();
	}
	
	
	
	@Override
	public List<Customers> getCustomerByfirstNameAndLastName(String firstName,String lastName)throws CustomerFirstnameLastnameNotFoundException {
		// TODO Auto-generated method stub
		List<Customers> customerOptional = customer_repo.findCustomerByfirstNameAndlastName(firstName,lastName);
		if (customerOptional.isEmpty()) {
			throw new CustomerFirstnameLastnameNotFoundException("Name not found");
		}
		return customerOptional;
	}
	
	
	
//public Customers updateCustomersById(int customersId, Customers updatedCustomers) {
//		Customers customers =customer_repo.findById(customersId).get();
//		if(customers!=null)
//		{
//			customers.setFirstName(updatedCustomers.getFirstName());
//			customers.setLastName(updatedCustomers.getLastName());
//			customers.setAddress(updatedCustomers.getAddress());
//			customers.setEmail(updatedCustomers.getEmail());
//			customers.setPhoneNumber(updatedCustomers.getPhoneNumber());
//		}
//		customer_repo.save(customers);
//		return updatedCustomers;
//    }
//	public Customers addCustomers(Customers customer) {
//		
//			return customer_repo.save(customer);
//		
//    }
	


	@Override
	public SuccessResponse updateCustomerById(int customerId,Customers updatedCustomers) throws CustomerIdNotFoundException, CustomerCannotBeUpdatedException {
		// TODO Auto-generated method stub
		Customers customers=customer_repo.findById(customerId).get();
		if(customers==null) {
			throw new CustomerIdNotFoundException("Validation Failed:Id not found");
		}
		if(!Validationclass.ValidationInt(updatedCustomers.getFirstName())){
			throw new CustomerCannotBeUpdatedException("Validation failed:First name should be string");
		}
		if(!Validationclass.ValidationInt(updatedCustomers.getLastName())){
			throw new CustomerCannotBeUpdatedException("Validation failed:Last name should be string");
		}
		
		if(!Validationclass.ValidationInt(updatedCustomers.getPhoneNumber())){
			throw new CustomerCannotBeUpdatedException("Validation failed:Phone number should be string");
		}
		customers.setFirstName(updatedCustomers.getFirstName());
		customers.setLastName(updatedCustomers.getLastName());
		customers.setPhoneNumber(updatedCustomers.getPhoneNumber());
		customers.setEmail(updatedCustomers.getEmail());
		customers.setAddress(updatedCustomers.getAddress());
		SuccessResponse s = new SuccessResponse();
		s.setTimestamp(LocalDate.now());
		s.setMessage("Data updated");
		s.setStatus("Success");
		return s;
	}		


	@Override
	public Optional<Pets> getAllPetsByCustomerId(int customer_id) throws CustomerIdNotFoundException {

		// TODO Auto-generated method stub
		List<Transactions> transactions = trans_repo.findByCustomer_CustomerId(customer_id);
        Iterator i=transactions.listIterator();
        Optional<Pets> pets = java.util.Optional.empty();
        while(i.hasNext()) {
        	Transactions t=(Transactions) i.next();
        	int petid=t.getPet().getPetId();
        	pets=pets_repo.findById(petid);
        }
        //Optional<Pets> pets=pets_repo.findById(petid);
        if(pets.isEmpty()) {
        	throw new CustomerIdNotFoundException("Validation Failed:Customer id not found");
        }
        return pets;
	}

//	public Customers addCustomers(Customers customers) {
//		// TODO Auto-generated method stub
//		
//			return customer_repo.save(customers);		
//	}
	
	@Override
	public SuccessResponse addCustomers(Customers customers) throws CustomersCannotBeAddedException {
		try {
			Customers customer =customer_repo.save(customers);
			if(!Validationclass.ValidationInt(customer.getFirstName())){
				throw new CustomersCannotBeAddedException("Validation failed:First name should be string");
			}
			if(!Validationclass.ValidationInt(customer.getLastName())){
				throw new CustomersCannotBeAddedException("Validation failed:Last name should be string");
			}
			if(!Validationclass.ValidationInt(customer.getPhoneNumber())){
				throw new CustomersCannotBeAddedException("Validation failed:Phone number should be string");
			}
			SuccessResponse s = new SuccessResponse();
			s.setTimestamp(LocalDate.now());
			s.setMessage("Data added");
			s.setStatus("Success");
			return s;
		}
		catch(Exception e) {
			throw new CustomersCannotBeAddedException("Validation failed:Employee cannot be added"+e.getMessage());
		}
	}
@Override
public List<Transactions> getCustomersByTransactionStatus(transaction_status status)
		throws CustomerTransactionStatusNotFoundException {
	// TODO Auto-generated method stub
	return null;
}

	
	
	
	
	
	
}



