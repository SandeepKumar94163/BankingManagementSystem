package com.bankpr.daoImpl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bankpr.dao.Transactions;
import com.bankpr.model.Customer;

public class TransactionImpl implements Transactions {

	private SessionFactory sessionFactory; // SessionFactory object to manage sessions with the database

	public TransactionImpl() { // Constructor for TransactionImpl class
		this.sessionFactory = new Configuration().configure().buildSessionFactory(); // Initialize sessionFactory using
																						// Hibernate configuration
	}

	@Override
	public Optional<Customer> findById(Long id) { // Method to find a customer by ID
		try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
			return Optional.ofNullable(session.get(Customer.class, id)); // Retrieve the customer with the provided ID
																			// and return it as an Optional
		}
	}

	@Override
	public void withdraw(Customer customer, double amount) { // Method to withdraw money from a customer's account
		try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
			Transaction transaction = session.beginTransaction(); // Begin a database transaction
			customer = session.get(Customer.class, customer.getCustomerId()); // Retrieve the customer from the database
			double currentBalance = customer.getBalance(); // Get the current balance of the customer
			if (currentBalance >= amount) { // Check if there are sufficient funds in the account
				customer.setBalance(currentBalance - amount); // Update the balance after withdrawal
				session.update(customer); // Update the customer entity in the database
				transaction.commit(); // Commit the transaction
			} else {
				transaction.rollback(); // Rollback the transaction if there are insufficient funds
				throw new IllegalArgumentException("Insufficient balance"); // Throw an exception indicating
																			// insufficient balance
			}
		}
	}

	@Override
	public void deposit(Customer customer, double amount) { // Method to deposit money into a customer's account
		try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
			Transaction transaction = session.beginTransaction(); // Begin a database transaction
			customer = session.get(Customer.class, customer.getCustomerId()); // Retrieve the customer from the database
			double currentBalance = customer.getBalance(); // Get the current balance of the customer
			customer.setBalance(currentBalance + amount); // Update the balance after deposit
			session.update(customer); // Update the customer entity in the database
			transaction.commit(); // Commit the transaction
		}
	}

	@Override
	public double getBalance(Customer customer) { // Method to get the balance of a customer's account
		try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
			Customer balance = session.get(Customer.class, customer.getCustomerId()); // Retrieve the customer from the
																						// database
			return balance.getBalance(); // Return the balance of the customer's account
		}
	}
}