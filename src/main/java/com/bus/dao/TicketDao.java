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

	public boolean addTicket(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
			ticket.setCheckstatus("Pending");
			System.out.println("dao" + ticket.getCheckstatus());

			session.save(ticket);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelTicket(int pid) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete from Ticket where pid = :pid");
            query.setParameter("pid", pid);
            int rowsAffected = query.executeUpdate();
            tx.commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public List<TicketDetails> viewTicket() {
        try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery(
					"SELECT t, c.busno, c.contact FROM Ticket t INNER JOIN Checkbus c ON t.busid = c.busid");
            List<Object[]> results = query.list();
            List<TicketDetails> ticketDetailsList = new ArrayList<>();
            for (Object[] result : results) {
                Ticket ticket = (Ticket) result[0];
                String busno = (String) result[1];
                Long contact = (Long) result[2];
                Checkbus checkbus = new Checkbus(contact, busno);
                TicketDetails ticketDetails = new TicketDetails(ticket, checkbus);
                ticketDetailsList.add(ticketDetails);
            }
            return ticketDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Object[]> viewTicketByPid(int pid) {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createQuery("select t.firstlocation, t.lastlocation, t.bustype, t.date from Ticket t where t.pid = :pid", Object[].class);
            query.setParameter("pid", pid);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
