package com.bankpr.service;

import java.util.Optional;

import com.bankpr.model.Customer;

public interface TransactionService {
	public Optional<Customer> findById(Long id);

	public void withdraw(Customer customer, double amount);

	public void deposit(Customer customer, double amount);

	public double getBalance(Customer customer);
}
