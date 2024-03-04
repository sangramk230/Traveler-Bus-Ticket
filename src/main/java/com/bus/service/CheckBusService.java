package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dao.CheckBusDao;
import com.bus.entity.Checkbus;
import com.bus.entity.Ticket;

@Service
public class CheckBusService {

	@Autowired
	CheckBusDao checkBusDao;


	public boolean addBus(Checkbus checkbus) {
		boolean bb = checkBusDao.addBus(checkbus);
		System.out.println("sesese" + bb);
		if (bb) {
			return true;
		} else {
			return false;
		}
	}

	public List<Checkbus> isSeatAvailable(String from, String to, String bustype) {
		List<Checkbus> bb = checkBusDao.isSeatAvailable(from, to, bustype);
		System.out.println("Service :" + to + " - " + from + " " + bustype);
		if (bb == null) {
			return null;
		} else {
			return bb;
		}
	}

	public List<Checkbus> allBus() {
		List<Checkbus> bb = checkBusDao.allBus("Valid");
		if (bb == null) {
			return null;
		} else {
			return bb;
		}
	}
	public List<Ticket> deleteByInvalid() {
		return checkBusDao.deleteByInvalid("Invalid");
	}

}
