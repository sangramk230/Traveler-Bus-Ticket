package com.bus.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	private String email;
	private Integer id;
	private String name, gender, password;
	private Date dob;
	private String phoneno;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, Integer id, String name, String gender, String password, Date dob, String phoneno) {
		super();
		this.email = email;
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.dob = dob;
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneno() {
		return this.phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

}
