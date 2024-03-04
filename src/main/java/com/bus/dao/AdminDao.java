package com.bus.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bus.entity.Admin;
import com.bus.entity.AdminViewDetails;
import com.bus.entity.Ticket;
import com.bus.entity.User;

@Repository
public class AdminDao {
	@Autowired
	private SessionFactory sessionFactory;

	public String checkEmailFrm(String email) {

		Session session = sessionFactory.openSession();

		Admin checkEmail = session.get(Admin.class, email);

		if (checkEmail == null) {
			return null;

		} else {
			return checkEmail.getPassword();

		}
	}

	public List<AdminViewDetails> adminView(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "SELECT t, u FROM Ticket t INNER JOIN User u ON t.id = u.id Where u.id = : id AND t.id =: tid";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("tid", id);

		List<Object[]> results = query.list();

		List<AdminViewDetails> adminViewDetailsList = new ArrayList<>();
		for (Object[] result : results) {
			Ticket ticket = (Ticket) result[0];
			User user = (User) result[1];

			AdminViewDetails adminViewDetails = new AdminViewDetails(ticket, user);
			adminViewDetailsList.add(adminViewDetails);
		}

		tx.commit();
		session.close();
		System.out.println(adminViewDetailsList);
		return adminViewDetailsList;
	}

	public List<User> viewUser() {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("FROM User");
		List<User> userList = query.list();
		tx.commit();
		session.close();
		return userList;
	}

	public boolean updateTicketStatus(Ticket ticket) {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			session.update(ticket);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<AdminViewDetails> getTicketsByStatus(String checkstatus) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "SELECT t, u FROM Ticket t INNER JOIN User u ON t.id = u.id  Where  t.checkstatus =: checkstatus";
		Query query = session.createQuery(hql);
		query.setParameter("checkstatus", checkstatus);
		List<Object[]> results = query.list();

		List<AdminViewDetails> adminViewDetailsList = new ArrayList<>();
		for (Object[] result : results) {
			Ticket ticket = (Ticket) result[0];
			User user = (User) result[1];

			AdminViewDetails adminViewDetails = new AdminViewDetails(ticket, user);
			adminViewDetailsList.add(adminViewDetails);
		}

		tx.commit();
		session.close();
		System.out.println(adminViewDetailsList);
		return adminViewDetailsList;
	}

	public Ticket getTicketById(int pid) {
		System.out.println("ddd" + pid);
		Session session = sessionFactory.openSession();
		System.out.println(session.get(Ticket.class, pid));
		return session.get(Ticket.class, pid);
	}
}
