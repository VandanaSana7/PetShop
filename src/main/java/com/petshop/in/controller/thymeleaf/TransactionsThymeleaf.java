package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.FailedTransNotFoundException;
import com.petshop.in.exceptions.categorytransaction.SuccessTransNotFoundException;
import com.petshop.in.model.Transactions;
import com.petshop.in.service.TransactionService;


@Controller
@RequestMapping("/transactions")
public class TransactionsThymeleaf {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/getAllTransactions")
	public String getAllTransactions(Model model) 
	{
		List<Transactions> transactionsList = transactionService.retreiveAllTransactions();
		model.addAttribute("AllTransactions", transactionsList);
		return "TransactionsList";
	}
	
	@GetMapping("/form")
	public String showTransactionIdForm() {
		return "TransactionIdForm";
	}

	@GetMapping("/getTransactionId/transactionId")
	public String getTransactionById(@RequestParam("transactionId") Integer transactionId, Model model)
	{
		Transactions transaction = transactionService.retreiveTransById(transactionId);
		model.addAttribute("TransactionById", transaction);
		return "TransactionById";
	}
	
	@GetMapping("/customeridform")
	public String showTransactionByCustomerIdForm() {
		return "TransactionByCustomerIdForm";
	}

	@GetMapping("/getTransaction/customerId")
	public String getTransactionByCustomerId(@RequestParam("customerId") Integer customerId, Model model) throws CustomerIdNotFoundException 
	{
		Transactions transaction = transactionService.retreiveCustById(customerId);
		model.addAttribute("TransactionByCustomerId", transaction);
		return "TransactionByCustomerId";
	}
	
	@GetMapping("/getAllSuccessfulTransactions")
	public String getAllSuccessfulTransactions(Model model) throws SuccessTransNotFoundException 
	{
		List<Transactions> transactionsList = transactionService.retrieveAllsuccessfulTransactions();
		model.addAttribute("AllSuccessfulTransactions", transactionsList);
		return "SuccessfulTransactionsList";
	}
	
	@GetMapping("/getAllFailedTransactions")
	public String getAllFailedTransactions(Model model) throws FailedTransNotFoundException
	{
		List<Transactions> transactionsList = transactionService.retrieveAllCancelledTransactions();
		model.addAttribute("AllFailedTransactions", transactionsList);
		return "FailedTransactionsList";
	}
	
	@GetMapping("/posttransactionform")
	public String showTransactionForm()
	{
		return "PostTransactionForm";
	}
	
	@PostMapping("/add")
	public String addTransaction(Model model, @ModelAttribute("transaction") Transactions transaction) throws MismatchDataTypeException
	{
		SuccessResponse s = transactionService.addTransaction(transaction);
		model.addAttribute("AddTransaction", s);
		return "PostAddTransaction";
	}
	
	
}
