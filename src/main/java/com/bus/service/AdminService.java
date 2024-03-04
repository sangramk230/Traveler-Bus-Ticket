package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dao.AdminDao;
import com.bus.entity.AdminViewDetails;
import com.bus.entity.Ticket;
import com.bus.entity.User;

@Service
public class AdminService {
	@Autowired
	private AdminDao daoMethods;

	public boolean login(String email, String password) {

		String checkEmail = daoMethods.checkEmailFrm(email);
		if (checkEmail.equals(password)) {
			return true;
		} else {
			return false;

		}
	}

	public List<User> viewUser() {
		return daoMethods.viewUser();

	}

	public List<AdminViewDetails> adminView(int id) {
		return daoMethods.adminView(id);

	}

	public boolean updateTicketStatus(int pid, String newCheckstatus) {
		Ticket ticket = daoMethods.getTicketById(pid);
		System.out.println(pid + "" + newCheckstatus);
		if (ticket == null) {
			return false;
		} else {
			ticket.setCheckstatus(newCheckstatus);
			return daoMethods.updateTicketStatus(ticket);
		}
	}

	public List<AdminViewDetails> getPendingTickets() {
		return daoMethods.getTicketsByStatus("Pending");
	}
}
