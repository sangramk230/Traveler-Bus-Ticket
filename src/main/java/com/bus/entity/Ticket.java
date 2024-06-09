package com.bus.entity;



import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ticket {

	@Id
	private Long pid;
	private String firstlocation;
	private String lastlocation;
	private String bustype;
	private Date date;
	private Integer addseat;
	private Long busid;
	private Integer id;
	private Double price;
	private String status;
	private String checkstatus;


	public Ticket() {
		super();
	}


	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getAddseat() {
		return this.addseat;
	}

	public void setAddseat(Integer addseat) {
		this.addseat = addseat;
	}

	public Long getBusid() {
		return this.busid;
	}

	public void setBusid(Long busid) {
		this.busid = busid;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

}
