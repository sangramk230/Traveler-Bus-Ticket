package com.bus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ticket {
	@Id
	int pid;
	private int addseat, id, busid;
	private String firstlocation, lastlocation, bustype, date, status, checkstatus;
	private double price;

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getAddseat() {
		return this.addseat;
	}

	public void setAddseat(int addseat) {
		this.addseat = addseat;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusid() {
		return this.busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public String getFirstlocation() {
		return this.firstlocation;
	}

	public void setFirstlocation(String firstlocation) {
		this.firstlocation = firstlocation;
	}

	public String getLastlocation() {
		return this.lastlocation;
	}

	public void setLastlocation(String lastlocation) {
		this.lastlocation = lastlocation;
	}

	public String getBustype() {
		return this.bustype;
	}

	public void setBustype(String bustype) {
		this.bustype = bustype;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckstatus() {
		return this.checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [pid=" + this.pid + ", addseat=" + this.addseat + ", id=" + this.id + ", busid=" + this.busid
				+ ", firstlocation=" + this.firstlocation + ", lastlocation=" + this.lastlocation + ", bustype="
				+ this.bustype + ", date=" + this.date + ", status=" + this.status + ", checkstatus=" + this.checkstatus
				+ ", price=" + this.price + "]";
	}

}
