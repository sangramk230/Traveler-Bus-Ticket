package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dao.AdminDao;
import com.bus.entity.AdminViewDetails;
import com.bus.entity.Checkbus;
import com.bus.entity.Ticket;
import com.bus.entity.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class AdminService {
	@Autowired
	private AdminDao daoMethods;

	private static final String ACCOUNT_SID = "AC533c114e1ab69f54a4b7e5081a20c7fc";

	private static final String AUTH_TOKEN = "52cd9d4d2ed5f52518a7129ab0c02eda";

	private static final String FROM_PHONE_NUMBER = "+12185203533";

	public boolean login(String email, String password) {
		String checkEmail = daoMethods.checkEmailFrm(email);
		return checkEmail.equals(password);
	}

	public List<User> viewUser() {
		return daoMethods.viewUser();
	}

	public List<AdminViewDetails> adminView(int id) {
		return daoMethods.adminView(id);
	}

	public boolean updateTicketStatus(int pid, String phoneno, String newCheckstatus) {
		Ticket ticket = daoMethods.getTicketById(pid);
		if (ticket != null) {
			ticket.setCheckstatus(newCheckstatus);
			sendTicketViaWhatsApp(phoneno, ticket);
			return daoMethods.updateTicketStatus(ticket);
		} else {
			return false;
		}
	}

	private void sendTicketViaWhatsApp(String phoneno, Ticket ticket) {
		try {
			System.out.println(phoneno);
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			String message = formatTicketAsWhatsAppMessage(ticket);
			Message.creator(new PhoneNumber(phoneno), new PhoneNumber(FROM_PHONE_NUMBER), message)
					.create();
		} catch (Exception e) {
			e.printStackTrace();

			throw new RuntimeException("Failed to send WhatsApp message: " + e.getMessage());
		}
	}

	private String formatTicketAsWhatsAppMessage(Ticket ticket) {
		Checkbus checkbus = new Checkbus();
return "\n\s\s\sTicket details \s\s\s  " + "\n\s\s\sTicket No. :" + ticket.getBusid() + "\n\s\s\sFirst Location :"
		+ ticket.getFirstlocation()
				+ "\n\s\s\sLast Location :" + ticket.getLastlocation() + "\n\s\s\sSeats : " + ticket.getAddseat()
				+ "\n\s\s\sBus Type :" + ticket.getBustype() + "\n\s\s\sDate : " + ticket.getDate()
		+ "\n\s\s\sStatus : " + ticket.getCheckstatus() + "\n\s\s\sPrice : " + ticket.getPrice() + "rs"
		+ "\n\s\s\sTravalers Contact"
		+ "\n\s\s\sBus No.  :" + checkbus.getBusno() + "\n\s\s\sTravalers Contact  :" + checkbus.getContact()
		+ "\n\s\s\sEmail : Sangramk230@gmail.com";
	}

	public List<AdminViewDetails> getPendingTickets() {
		return daoMethods.getTicketsByStatus("Pending");
	}

	public boolean cancelBus(int busid) {
		return daoMethods.cancelBus(busid);
	}

	public List<User> profile() {
		return daoMethods.profile();
	}
}