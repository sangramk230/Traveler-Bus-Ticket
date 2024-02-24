package com.bus.dao;

import java.util.LinkedList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bus.entity.Checkbus;

@Repository
public class CheckBusDao {

	@Autowired
	SessionFactory sessionFactory;
	Checkbus checkbus;

	public boolean addBus(Checkbus checkbus) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			if (checkbus != null) {
				session.save(checkbus);
				tx.commit();
				return true;
			} else {
				session.save(checkbus);
				tx.rollback();
				return false;
			}
		} catch (Exception e) {
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

}
