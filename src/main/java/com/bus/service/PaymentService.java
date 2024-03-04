package com.bus.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bus.entity.Payment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

	private static final String SECRET_ID = "rzp_test_3F5h7UOSSPULpF";
	private static final String SECRET_KEY = "6aD6XihHQ0dvSaDbPvcHNf1k";
	private static final String CURRENCY = "INR";
	
	public Payment createTicketTranscation(double amount) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", (amount * 100));
		jsonObject.put("currency", CURRENCY);

		RazorpayClient razorpayClient = new RazorpayClient(SECRET_ID, SECRET_KEY);
		Order order = razorpayClient.orders.create(jsonObject);
		System.out.println(order);
		Payment paymentt = prepareTranscation(order);
		return paymentt;

	}

	private Payment prepareTranscation(Order order) {
		String paymentid = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");

		Payment payment = new Payment(paymentid, SECRET_ID, currency, amount);
		return payment;
	}


}
