package com.petshop.in.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;
 
import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import java.util.Optional;
 
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.petshop.in.enums.transaction_status;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.TransactionRepository;
import com.petshop.in.serviceimpl.TransactionServiceImpl;


 


public class TransactionServiceTest {
 
    private TransactionServiceImpl transactionService;

    private TransactionRepository transactionRepository;
 

    @Test

    void testRetrieveCustById() {

        Transactions transaction = new Transactions();

        int customerId = 1;

        when(transactionRepository.findById(customerId)).thenReturn(Optional.of(transaction));
 
       

    }

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void testRetrieveAllCancelledTransactions() {
        List<Transactions> transactions = new ArrayList<>();
        Transactions transaction1 = new Transactions();
        transaction1.setTransactionStatus(transaction_status.Failed);
        transactions.add(transaction1);

        when(transactionRepository.findAll()).thenReturn(transactions);


    }

    @Test
    public void testRetrieveAllCancelledTransactionsNotFound() {
        List<Transactions> transactions = new ArrayList<>();
        Transactions transaction1 = new Transactions();
        transaction1.setTransactionStatus(transaction_status.Success);
        transactions.add(transaction1);

        when(transactionRepository.findAll()).thenReturn(transactions);

        
    }

    @Test
    public void testRetrieveAllSuccessfulTransactions() {
        List<Transactions> transactions = new ArrayList<>();
        Transactions transaction1 = new Transactions();
        transaction1.setTransactionStatus(transaction_status.Success);
        transactions.add(transaction1);

        when(transactionRepository.findAll()).thenReturn(transactions);

    }

    @Test
    public void testRetrieveAllSuccessfulTransactionsNotFound() {
        List<Transactions> transactions = new ArrayList<>();
        Transactions transaction1 = new Transactions();
        transaction1.setTransactionStatus(transaction_status.Failed);
        transactions.add(transaction1);

        when(transactionRepository.findAll()).thenReturn(transactions);


    }

    @Test
    public void testAddTransaction() {
        Transactions transaction = new Transactions();
        //transaction.setTransactionDate(LocalDate.now().toString());
        transaction.setTransactionStatus(transaction_status.Success);
        
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        
       
    }
}




 