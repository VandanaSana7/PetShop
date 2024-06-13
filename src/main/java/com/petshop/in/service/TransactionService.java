package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.FailedTransNotFoundException;
import com.petshop.in.exceptions.categorytransaction.SuccessTransNotFoundException;
import com.petshop.in.model.Transactions;
import com.petshop.in.model.Vaccinations;

public interface TransactionService {
	
	public List<Transactions> retreiveAllTransactions();
	public List<Vaccinations> getAllVaccinationsAvailable();
	public Transactions retreiveCustById(int customerId) throws CustomerIdNotFoundException ;
	public List<Transactions> retrieveAllCancelledTransactions() throws FailedTransNotFoundException;
	
	public List<Transactions> getCustomersByTransactionStatus();
	Transactions retreiveTransById(int transactionId);
	SuccessResponse addTransaction(Transactions transaction) throws MismatchDataTypeException;
	List<Transactions> retrieveAllSuccessfulTransactions() throws SuccessTransNotFoundException;
	List<Transactions> retrieveAllsuccessfulTransactions();

	

}
