package com.bus.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bus.entity.Checkbus;
import com.bus.entity.Ticket;
import com.bus.entity.TicketDetails;

@Repository
public class TicketDao {
    @Autowired
    private SessionFactory sessionFactory;

	private Session session;

	private Integer userIdCreate(Object email) {
		Session session = sessionFactory.openSession();
		Query<Integer> query = session.createQuery("SELECT id FROM User WHERE email = :email");
		query.setParameter("email", email);
		return query.uniqueResult();

	}
	public Boolean addTicket(Ticket ticket, Object email) {
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Integer checkid = userIdCreate(email);
			if (checkid != null) {
				ticket.setCheckstatus("Pending");
				ticket.setId(checkid);
				System.out.println("fgfgfgfffff" + ticket.getId());
				session.persist(ticket);
				tx.commit();
				return true;

			} else {
				tx.rollback();
				return false;
			}

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public Boolean cancelTicket(Integer pid, Object email) {
		try {
			session = sessionFactory.openSession();
			Integer checkid = userIdCreate(email);
            Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete from Ticket where pid = :pid AND id =: checkid");
            query.setParameter("pid", pid);
			query.setParameter("checkid", checkid);
			Integer rowsAffected = query.executeUpdate();
            tx.commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public List<TicketDetails> viewTicket(Object email) {
		try {
			session = sessionFactory.openSession();
			Integer checkid = userIdCreate(email);
			String ticketQuery = "SELECT t, c.busno, c.contact FROM Ticket t INNER JOIN Checkbus c ON t.busid = c.busid WHERE t.id = :userId";

				Query query = session.createQuery(ticketQuery);
				query.setParameter("userId", checkid);

				List<Object[]> results = query.list();
				List<TicketDetails> ticketDetailsList = new ArrayList<>();

				for (Object[] result : results) {
					Ticket ticket = (Ticket) result[0];
					String busNo = (String) result[1];
					String contact = (String) result[2];
					Checkbus checkbus = new Checkbus(contact, busNo);
					TicketDetails ticketDetails = new TicketDetails(ticket, checkbus);
					ticketDetailsList.add(ticketDetails);
				}

				return ticketDetailsList;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<Object[]> viewTicketByPid(Integer pid) {
		try {
			session = sessionFactory.openSession();
			Query<Object[]> query = session.createQuery(
					"select t.firstlocation, t.lastlocation, t.bustype, t.date from Ticket t where t.pid = :pid",
					Object[].class);
			query.setParameter("pid", pid);
			return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}