package com.bankpr.serviceImpl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bankpr.daoImpl.TransactionImpl;
import com.bankpr.model.Customer;
import com.bankpr.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	TransactionImpl transaction = new TransactionImpl();
	Customer customer = new Customer();

	private SessionFactory sessionFactory;

	public TransactionServiceImpl() {
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@Override
	public Optional<Customer> findById(Long id) {

		try (Session session = sessionFactory.openSession()) {
			transaction.findById(id);
			return Optional.ofNullable(session.get(Customer.class, id));
		}

	}

	@Override
	public void withdraw(Customer customer, double amount) {
		transaction.withdraw(customer, amount);

	}

	@Override
	public void deposit(Customer customer, double amount) {
		transaction.deposit(customer, amount);

	}

	@Override
	public double getBalance(Customer customer) {
//		transaction.getBalance(customer);
		try (Session session = sessionFactory.openSession()) {
			Customer balance = session.get(Customer.class, customer.getCustomerId());
			return balance.getBalance();
		}

	}

}
