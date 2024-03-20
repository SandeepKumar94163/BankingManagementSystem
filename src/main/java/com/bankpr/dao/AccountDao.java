package com.bankpr.dao;

import java.util.List;

import com.bankpr.model.Customer;

public interface AccountDao { // Declaration of interface named AccountDao
	
	// Method to create a new customer
	public void createCustomer(Customer customer);

	// Method to update an existing customer
	public void updateCustomer(Customer customer);

	// Method to delete a customer
	public void deleteCustomer(Customer customer);

	// Method to display all customers
	List<Customer> displayAllCustomer();

	// Method to get customer by account number
	Customer getByAccountNumber(String accountNumber);

}