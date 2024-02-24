package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dao.CheckBusDao;
import com.bus.entity.Checkbus;

@Service
public class CheckBusService {

	@Autowired
	CheckBusDao checkBusDao;


	public boolean addBus(Checkbus checkbus) {
		return checkBusDao.addBus(checkbus);
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
}
