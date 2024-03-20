package com.bankpr.daoImpl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bankpr.dao.UserDao;
import com.bankpr.model.User;
import com.bankpr.utils.HibernateUtil;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory; // SessionFactory object to manage sessions with the database

	public UserDaoImpl() { // Constructor for UserDaoImpl class
		this.sessionFactory = HibernateUtil.getSessionFactory(); // Initialize sessionFactory using HibernateUtil class
	}

	@Override
	public Optional<User> findByUsername(String email) { // Method to find a user by username (email)
		try (Session session = sessionFactory.openSession()) { // Open a session for database interaction
			Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class); // Create HQL query
																									// to retrieve user
																									// by email
			query.setParameter("email", email); // Set the email parameter in the query
			return query.uniqueResultOptional(); // Execute the query and return the result as an Optional
		}
	}

}