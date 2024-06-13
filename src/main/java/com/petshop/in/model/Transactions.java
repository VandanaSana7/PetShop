package com.petshop.in.model;



import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import com.petshop.in.enums.transaction_status;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
 
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;
 
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;
 
    @Column(name = "transaction_date",columnDefinition = "date")
    private Date transactionDate;
    @Column(columnDefinition = "Decimal(10,2)")
    private BigDecimal amount;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private transaction_status transactionStatus;
 
 
	public int getTransactionId() {
		return transactionId;
	}
 
 
	public Customers getCustomer() {
		return customer;
	}
 
 
	public Pets getPet() {
		return pet;
	}
 
 
	public Date getTransactionDate() {
		return transactionDate;
	}
 
 
	public BigDecimal getAmount() {
		return amount;
	}
 
 
	public transaction_status getTransactionStatus() {
		return transactionStatus;
	}
 
 
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
 
 
	public void setPet(Pets pet) {
		this.pet = pet;
	}
 
 
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
 
 
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
 
 
	public void setTransactionStatus(transaction_status transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

 
 
	public Transactions(int transactionId, Customers customer, Pets pet, Date transactionDate, BigDecimal amount,
			transaction_status transactionStatus) {
		super();
		this.transactionId = transactionId;
		this.customer = customer;
		this.pet = pet;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionStatus = transactionStatus;
	}
 
 
	public Transactions(Customers customer, Pets pet, Date transactionDate, BigDecimal amount,
			transaction_status transactionStatus) {
		super();
		this.customer = customer;
		this.pet = pet;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionStatus = transactionStatus;
	}
 
 
	public Transactions() {
		super();
	}
 
 
	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", customer=" + customer + ", pet=" + pet
				+ ", transactionDate=" + transactionDate + ", amount=" + amount + ", transactionStatus="
				+ transactionStatus + "]";
	}

}

