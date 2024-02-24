package com.bus.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bus.entity.User;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public User getUserByEmail(String email) {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM User WHERE email = :email", User.class).setParameter("email", email)
					.uniqueResult();
		} catch (Exception e) {
			// Handle exceptions or log them
			return null;
		}
	}

	public String loginUser(String emailFrm) {

		Session session = sessionFactory.openSession();

		User checkEmail = session.get(User.class, emailFrm);
		System.out.println(checkEmail);

		if (checkEmail != null) {
			return checkEmail.getPassword();

		} else {
			return null;

		}

	}



	public List<User> profile() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("From User");
		tx.commit();

		return query.list();

	}



}
