package com.bus.entity;

public class AdminViewDetails {
	private Ticket ticket;
	private User user;



public AdminViewDetails(Ticket ticket, User user) {
	super();
	this.ticket = ticket;
	this.user = user;

}

public AdminViewDetails() {
	super();
	// TODO Auto-generated constructor stub
}

public User getUser() {
	return this.user;
}

public void setUser(User user) {
	this.user = user;
}

public Ticket getTicket() {
	return this.ticket;
}

public void setTicket(Ticket ticket) {
	this.ticket = ticket;
}


}
