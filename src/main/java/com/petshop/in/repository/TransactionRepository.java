package com.petshop.in.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.enums.transaction_status;
import com.petshop.in.model.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer>{

	List<Transactions> findByCustomer_CustomerId(int customer_id);


	//List<Transactions> findCustomersByTransactionStatus(transaction_status status);

	List<Transactions> findByTransactionStatus(transaction_status status);

	
}
