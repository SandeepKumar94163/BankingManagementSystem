package com.bankpr.serviceImpl;

import java.util.List;

import com.bankpr.daoImpl.AccountDaoImpl;
import com.bankpr.model.Customer;
import com.bankpr.service.AccountService;

public class AccountServiceImpl implements AccountService {
	AccountDaoImpl accDao = new AccountDaoImpl();

	@Override
	public void createCustomer(Customer customer) {
		accDao.createCustomer(customer);

	}

	@Override
	public void updateCustomer(Customer customer) {
		accDao.updateCustomer(customer);

	}

	@Override
	public void deleteCustomer(Customer customer) {
		accDao.deleteCustomer(customer);

	}

	@Override
	public List<Customer> displayAllCustomer() {
		accDao.displayAllCustomer();
		return null;
	}

	@Override
	public Customer getByAccountNumber(String accountNumber) {
		accDao.getByAccountNumber(accountNumber);
		return null;
	}

}
