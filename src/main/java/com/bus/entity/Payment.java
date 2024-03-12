package com.bus.entity;

public class Payment {
	private String paymentid;
	private String key;
	private String currency;
	private Integer amount;
	public Payment(String paymentid, String key, String currency, Integer amount) {
		super();
		this.paymentid = paymentid;
		this.key = key;
		this.currency = currency;
		this.amount = amount;
	}


	public String getPaymentid() {
		return this.paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}



}
