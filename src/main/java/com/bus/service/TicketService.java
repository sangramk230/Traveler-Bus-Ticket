package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dao.TicketDao;
import com.bus.entity.Ticket;
import com.bus.entity.TicketDetails;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;

	public boolean addTicket(Ticket ticket) {
		return ticketDao.addTicket(ticket);
	}

	public boolean cancelTicket(int pid) {
		return ticketDao.cancelTicket(pid);
	}
	public List<TicketDetails> viewTicket() {
		return ticketDao.viewTicket();
	}
	public List<Object[]> viewTicketByPid(int pid) {
		return ticketDao.viewTicketByPid(pid);
	}

}
