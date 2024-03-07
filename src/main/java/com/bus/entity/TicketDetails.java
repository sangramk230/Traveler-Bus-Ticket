package com.bus.entity;

public class TicketDetails {

	private Ticket ticket;
	private Checkbus checkbus;


	public TicketDetails(Ticket ticket, Checkbus checkbus) {
		super();
		this.ticket = ticket;
		this.checkbus = checkbus;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Checkbus getCheckbus() {
		return this.checkbus;
	}

	public void setCheckbus(Checkbus checkbus) {
		this.checkbus = checkbus;
	}

	@Override
	public String toString() {
		return "TicketDetails [ticket=" + this.ticket + ", checkbus=" + this.checkbus + "]";
	}



}
