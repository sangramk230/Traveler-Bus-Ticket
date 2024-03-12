package com.bus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Checkbus {
@Id
private Long busid;
private Long avilableseat;
private String contact;
private double price;
private String bustype, busno, firstlocation, lastlocation, date, status;

public Checkbus() {
	super();
	// TODO Auto-generated constructor stub
}


public Checkbus(String contact, String busno) {
	super();
	this.contact = contact;
	this.busno = busno;
}

public Long getBusid() {
	return this.busid;
}

public void setBusid(Long busid) {
	this.busid = busid;
}

public Long getAvilableseat() {
	return this.avilableseat;
}

public void setAvilableseat(Long avilableseat) {
	this.avilableseat = avilableseat;
}

public String getContact() {
	return this.contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public double getPrice() {
	return this.price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getBustype() {
	return this.bustype;
}

public void setBustype(String bustype) {
	this.bustype = bustype;
}

public String getBusno() {
	return this.busno;
}

public void setBusno(String busno) {
	this.busno = busno;
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

@Override
public String toString() {
	return "Checkbus [busid=" + this.busid + ", avilableseat=" + this.avilableseat + ", contact=" + this.contact
			+ ", price=" + this.price + ", bustype=" + this.bustype + ", busno=" + this.busno + ", firstlocation="
			+ this.firstlocation + ", lastlocation=" + this.lastlocation + ", date=" + this.date + ", status="
			+ this.status + "]";
}

}
