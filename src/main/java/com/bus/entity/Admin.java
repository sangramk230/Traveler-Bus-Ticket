package com.bus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
	@Id
	private String email;
	private int aid;
	private String name, password;
	private Long contact;

	public Admin() {
		super();
	}

	public int getAid() {
		return this.aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getContact() {
		return this.contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}


	@Override
	public String toString() {
		return "Admin [aid=" + this.aid + ", name=" + this.name + ", email=" + this.email + ", password="
				+ this.password + ", contact=" + this.contact + "]";
	}

}