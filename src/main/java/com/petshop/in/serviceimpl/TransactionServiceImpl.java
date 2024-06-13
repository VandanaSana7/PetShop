package com.petshop.in.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.in.enums.transaction_status;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.categorytransaction.CategoryAddException;
import com.petshop.in.exceptions.categorytransaction.CategoryIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.CategoryNotFoundException;
import com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.FailedTransNotFoundException;
import com.petshop.in.exceptions.categorytransaction.SuccessTransNotFoundException;
import com.petshop.in.exceptions.categorytransaction.TransactionCannotbeAddedException;
import com.petshop.in.exceptions.categorytransaction.TransactionsIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.TransactionsNotFoundException;
import com.petshop.in.model.PetCategories;
import com.petshop.in.model.Transactions;
import com.petshop.in.model.Vaccinations;
import com.petshop.in.repository.PetCatogeriesRepository;
import com.petshop.in.repository.TransactionRepository;
import com.petshop.in.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	

			@Autowired
			TransactionRepository transaction_repo;

		
			public TransactionServiceImpl(TransactionRepository transactionRepository) {
				// TODO Auto-generated constructor stub
			}


			//get all
			@Override
			public List<Transactions> retreiveAllTransactions(){
				//return transaction_repo.findAll();
				List<Transactions> trans = transaction_repo.findAll();
				if(trans.isEmpty())
					throw new TransactionsNotFoundException("Validation failed");
				return trans;
			}
					
			
			//get by transId
			@Override
			public Transactions retreiveTransById(int transactionId) {
				try {
					Transactions trans = transaction_repo.findById(transactionId).get();
					return trans;
				}
				catch(NoSuchElementException e) {
			    		throw new TransactionsIdNotFoundException("Validation Failed");
			    		//return transaction_repo.findById(transactionId).get();
			    	}
			}
			
			//get by custId
			@Override
			public Transactions retreiveCustById(int customerId) throws CustomerIdNotFoundException {
				try {
					Transactions trans1 = transaction_repo.findById(customerId).get();
					return trans1;
				}
				catch(NoSuchElementException e) {
			    		throw new CustomerIdNotFoundException("Validation Failed");
			    		//return transaction_repo.findById(transactionId).get();
			    	}
			}
			
			//failed
			@Override
			public List<Transactions> retrieveAllCancelledTransactions() throws FailedTransNotFoundException {
		        List<Transactions> cancelledTransactions = new ArrayList<>();
		        //List<Transactions> allTransactions = transactionRepo.findAll();
		        for (Transactions transaction : retreiveAllTransactions()) {
		            if (transaction.getTransactionStatus() == transaction_status.Failed) {
		            	cancelledTransactions.add(transaction);
		            }
		        }
		        if (cancelledTransactions.isEmpty()) {
		        	 throw new FailedTransNotFoundException("Validation Failed");
		        }
		        return cancelledTransactions;
		    }
		
			

			
			//success
			@Override
			public List<Transactions> retrieveAllSuccessfulTransactions() throws SuccessTransNotFoundException {
		        List<Transactions> successfulTransactions = new ArrayList<>();
		        
		        for (Transactions transaction : retreiveAllTransactions()) {
		            if (transaction.getTransactionStatus() == transaction_status.Success) {
		                successfulTransactions.add(transaction);
		            }
		        }
		        if (successfulTransactions.isEmpty()) {
		            throw new SuccessTransNotFoundException("Validation Failed");
		        }
		        return successfulTransactions;
		    }

		
			@Override
			public SuccessResponse addTransaction(Transactions transaction) throws MismatchDataTypeException {
			    try {
			        // Perform validation
			        if (!Validationclass.isValidDateFormat(transaction.getTransactionDate())) {
			            throw new MismatchDataTypeException("Invalid status format");
			        }

		        if(Validationclass.validateTransactionStatus(transaction.getTransactionStatus())) { // Enum value passed
			        	throw new MismatchDataTypeException("Invalid date format");
			        }

			        Transactions transobj = transaction_repo.save(transaction);
			        SuccessResponse s = new SuccessResponse();
			        s.setMessage("Data Updated" + transobj);
			        s.setStatus("Success");
			        s.setTimestamp(LocalDate.now());
			        return s;
			    } catch (MismatchDataTypeException e) {
			        throw e;
			    } catch (Exception e) {
			        throw new TransactionCannotbeAddedException("Validation Failed");
			    }
			}


			@Override
			public List<Vaccinations> getAllVaccinationsAvailable() {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public List<Transactions> retrieveAllsuccessfulTransactions() {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public List<Transactions> getCustomersByTransactionStatus() {
				// TODO Auto-generated method stub
				return null;
			}


}