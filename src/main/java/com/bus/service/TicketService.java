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

	public boolean cancelTicket(int pid ,Object email) {
		return ticketDao.cancelTicket(pid, email);
	}

	public List<TicketDetails> viewTicket(Object email) {
		return ticketDao.viewTicket(email);
	}
	public List<Object[]> viewTicketByPid(int pid) {
		return ticketDao.viewTicketByPid(pid);
	}

	public boolean addTicket(Ticket ticket, Object email) {
		return ticketDao.addTicket(ticket, email);
	}



}