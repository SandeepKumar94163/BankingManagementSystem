package com.bankpr.daoImpl;
//importing important resources
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.bankpr.dao.AccountDao;
import com.bankpr.model.Customer;
import com.bankpr.utils.HibernateUtil;

public class AccountDaoImpl implements AccountDao {

	Scanner scanner = new Scanner(System.in);
	private SessionFactory sessionFactory;

	public AccountDaoImpl() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	// create or insert Customer and account
	@Override
	public void createCustomer(Customer customer) { // Method to create a new customer and account
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
	        transaction = session.beginTransaction(); // Begin transaction

	        Customer cust = new Customer(); // Create a new customer object
	        long cid = 10; // Set a default customer ID (this might need to be generated dynamically)
	        System.out.println("Enter Account Holder Name: ");
	        String cName = scanner.nextLine(); // Prompt user to enter account holder name
	        System.out.println("Enter Contact Number: ");
	        String cNumber = scanner.nextLine(); // Prompt user to enter contact number
	        System.out.println("Enter Account Number");
	        String account = scanner.next(); // Prompt user to enter account number
	        System.out.println("Enter opening Balance");
	        double balance = scanner.nextDouble(); // Prompt user to enter opening balance
	        scanner.nextLine(); // Consume newline character after reading double

	        System.out.println("Enter Email: ");
	        String cEmail = scanner.nextLine(); // Prompt user to enter email
	        System.out.println("Enter Address: ");
	        String cAddress = scanner.nextLine(); // Prompt user to enter address
	        cust.setCustomerId(cid); // Set customer ID
	        cust.setCustomerName(cName); // Set customer name
	        cust.setCustomerNumber(cNumber); // Set customer contact number
	        cust.setCustomerAccountNumber(account); // Set customer account number
	        cust.setBalance(balance); // Set initial balance
	        cust.setAccOpenDate(new Date()); // Set account opening date
	        cust.setCustomerEmail(cEmail); // Set customer email
	        cust.setCustomerAddress(cAddress); // Set customer address

	        session.save(cust); // Save customer object to the database
	        transaction.commit(); // Commit the transaction

	        System.out.println("Account created successfully");

	        session.close(); // Close the session

	    } catch (Exception e) {
	        System.out.println("Account Number Already Exist !"); // Handle case where account number already exists
	        System.out.println("Try again with Different Account Number");
	    }
	}
	// update Customer
	@Override
	public void updateCustomer(Customer customer) { // Method to update customer details
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
	        transaction = session.beginTransaction(); // Begin transaction
	        System.out.println("Enter Customer ID to update: ");
	        long cId = scanner.nextLong(); // Prompt user to enter the ID of the customer to update
	        scanner.nextLine(); // Consume the newline character

	        Customer cust = session.get(Customer.class, cId); // Retrieve customer from database based on ID

	        if (cust != null) { // Check if customer exists
	            System.out.println("Enter New Account Holder Name:");
	            String name = scanner.nextLine(); // Prompt user to enter new name
	            System.out.println("Enter New Contact Number:");
	            String number = scanner.nextLine(); // Prompt user to enter new contact number
	            System.out.println("Enter New Email:");
	            String email = scanner.nextLine(); // Prompt user to enter new email
	            System.out.println("Enter New Address:");
	            String address = scanner.nextLine(); // Prompt user to enter new address

	            cust.setCustomerName(name); // Update customer name
	            cust.setCustomerNumber(number); // Update customer contact number
	            cust.setCustomerEmail(email); // Update customer email
	            cust.setCustomerAddress(address); // Update customer address

	            session.update(cust); // Update customer in the database
	            transaction.commit(); // Commit the transaction
	            System.out.println("Customer updated successfully."); // Inform user of successful update
	        } else {
	            System.out.println("Customer with ID " + cId + " not found."); // Inform user if customer not found
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback(); // Rollback transaction in case of exception
	        }
	        e.printStackTrace(); // Print stack trace of exception
	    }
	}

	// Delete Customer
	@Override
	public void deleteCustomer(Customer customer) { // Method to delete a customer account
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
	        transaction = session.beginTransaction(); // Begin transaction
	        System.out.println("Enter Customer Id to delete: ");
	        long cid = scanner.nextLong(); // Prompt user to enter the ID of the customer to delete
	        Customer cust = session.get(Customer.class, cid); // Retrieve customer from database based on ID

	        if (cust != null) { // Check if customer exists
	            session.delete(cust); // Delete customer from the database
	            transaction.commit(); // Commit the transaction
	            System.out.println("Account deleted successfully."); // Inform user of successful deletion
	        } else {
	            System.out.println("Account with ID " + cid + " not found."); // Inform user if customer not found
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback(); // Rollback transaction in case of exception
	        }
	        e.printStackTrace(); // Print stack trace of exception
	    }
	}
//	Display List
	@Override
	public List<Customer> displayAllCustomer() { // Method to display all customer details
	    try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
	        String hql = "from Customer"; // HQL query to retrieve all customers
	        Query q = session.createQuery(hql); // Create a query object
	        List<Customer> customers = q.getResultList(); // Execute the query and get the list of customers
	        for (Customer customer : customers) { // Iterate through the list of customers
	            // Print details of each customer
	            System.out.println("ID: " + customer.getCustomerId() + ", Name: " + customer.getCustomerName()
	                    + ",Account Number : " + customer.getCustomerAccountNumber() + ",Account opening Date : "
	                    + customer.getAccOpenDate() + ",Account Balance : " + customer.getBalance() + ", Email: "
	                    + customer.getCustomerEmail() + ", Address: " + customer.getCustomerAddress()
	                    + ",Phone Number: " + customer.getCustomerNumber());
	        }
	        return customers; // Return the list of customers
	    } catch (Exception e) { // Catch any exceptions
	        e.printStackTrace(); // Print stack trace of the exception
	        return null; // Return null if an exception occurs
	    }
	}
	// particular account number
	@Override
	public Customer getByAccountNumber(String accountNumber) { // Method to retrieve customer by account number
	    try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
	        Query<Customer> query = session.createQuery("FROM Customer WHERE customerAccountNumber = :accNumber", Customer.class); // Create a query to fetch customer by account number
	        query.setParameter("accNumber", accountNumber); // Set the parameter for the account number
	        return query.uniqueResult(); // Execute the query and return the unique result (customer)
	    } catch (Exception e) { // Catch any exceptions
	        e.printStackTrace(); // Print stack trace of the exception
	    }
	    return null; // Return null if an exception occurs
	}
}
