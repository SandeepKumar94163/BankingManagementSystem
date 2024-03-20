package com.bankpr.dao;

import java.util.Optional;

import com.bankpr.model.Customer;
public interface Transactions { // Declaration of interface named Transactions
	
	// Method to find a customer by their ID
	Optional<Customer> findById(Long id);

	// Method to withdraw a specified amount from a customer's account
	void withdraw(Customer customer, double amount);

	// Method to deposit a specified amount into a customer's account
	void deposit(Customer customer, double amount);

	// Method to get the balance of a customer's account
	double getBalance(Customer customer);
}