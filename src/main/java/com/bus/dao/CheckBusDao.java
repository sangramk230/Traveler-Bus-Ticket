package com.bus.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bus.entity.Checkbus;
import com.bus.entity.Ticket;

@Repository
public class CheckBusDao {

	@Autowired
	SessionFactory sessionFactory;
	Checkbus checkbus;

	public boolean addBus(Checkbus checkbus) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.out.println("daooo" + checkbus);
			if (checkbus != null) {
				session.save(checkbus);
				tx.commit();

				return true;
			} else {
				tx.rollback();
				return false;
			}
	}

	public LinkedList<Checkbus> isSeatAvailable(String firstlocation, String lastlocation, String bustype) {
	    try {
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery(
	            "FROM Checkbus WHERE firstlocation = :firstlocation AND lastlocation = :lastlocation AND bustype = :bustype"
	        );
	        query.setParameter("firstlocation", firstlocation);
	        query.setParameter("lastlocation", lastlocation);
	        query.setParameter("bustype", bustype);
	        LinkedList<Checkbus> resultList = new LinkedList<>(query.list());
	        tx.commit();
			System.out.println(resultList);
	        System.out.println("Service :" + lastlocation + " - " + firstlocation + " " + bustype);
	        return resultList;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public List allBus() {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Checkbus");
			List<Checkbus> buses = query.list();
			tx.commit();

			return buses;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Ticket> deleteByInvalid(String status) {
		System.out.println(status);
		try (Session session = sessionFactory.openSession()) {
			Query<Ticket> query = session.createQuery("Delete Ticket WHERE status = :status");
			query.setParameter("status", status);
			System.out.println(query.list());
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
