package com.bankpr.service;

import java.util.List;

import com.bankpr.model.Customer;

public interface AccountService {
	public void createCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	List<Customer> displayAllCustomer();

	Customer getByAccountNumber(String accountNumber);
}
