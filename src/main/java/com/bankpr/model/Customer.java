package com.bankpr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(length = 30)
	private String customerName;
	@Column(length = 30)
	private String customerNumber;
	@Column(length = 30,unique=true)
	private String customerAccountNumber;
	private double balance;

	@Temporal(TemporalType.DATE)
	private Date accOpenDate;

	@Column(length = 30)
	private String customerEmail;

	@Column(length = 30)
	private String customerAddress;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getAccOpenDate() {
		return accOpenDate;
	}

	public void setAccOpenDate(Date accOpenDate) {
		this.accOpenDate = accOpenDate;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPhoneNumber="
				+ customerNumber + ", customerAccountNumber=" + customerAccountNumber + ", balance=" + balance
				+ ", accOpenDate=" + accOpenDate + ", customerEmail=" + customerEmail + ", customerAddress="
				+ customerAddress + "]";
	}

	public Customer(Long customerId, String customerName, String customerNumber, String customerAccountNumber,
			double balance, Date accOpenDate, String customerEmail, String customerAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.customerAccountNumber = customerAccountNumber;
		this.balance = balance;
		this.accOpenDate = accOpenDate;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
