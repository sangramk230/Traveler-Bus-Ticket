package com.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entity.Payment;
import com.bus.service.PaymentService;

@RestController
@CrossOrigin("https://creative-piroshki-7f24ae.netlify.app")
@RequestMapping("api/payment/")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@GetMapping("createTicket/{amount}")
	public Payment createTicketTranscation(@PathVariable Double amount) throws Exception {
		return paymentService.createTicketTranscation(amount);

	}
}

