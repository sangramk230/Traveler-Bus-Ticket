package com.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entity.Ticket;
import com.bus.entity.TicketDetails;
import com.bus.service.TicketService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/ticket/")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@RequestMapping("add")
	public ResponseEntity<Boolean> addTicket(@RequestBody Ticket ticket) throws Exception {
		HttpSession session = UserController.httpsession;
		session.getAttribute("loggedInUser");
		System.out.println(session);
		boolean bb = ticketService.addTicket(ticket, session.getAttribute("loggedInUser"));
		System.out.println("ccc" + ticket.getCheckstatus());
		if (bb) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("cancel/{pid}")
	public ResponseEntity<Boolean> cancelTicket(@PathVariable int pid) throws Exception {
		HttpSession session = UserController.httpsession;
		session.getAttribute("loggedInUser");
		if (session != null) {
			boolean canceled = ticketService.cancelTicket(pid, session.getAttribute("loggedInUser"));
			if (canceled) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
		} else {
			// Handle unauthorized access
			return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		}
	}
	@GetMapping("view")
	public ResponseEntity<List<TicketDetails>> viewTicket() throws Exception {
		HttpSession session = UserController.httpsession;
		session.getAttribute("loggedInUser");

		if (session != null) {
			System.out.println(session);
			return new ResponseEntity<List<TicketDetails>>(
					ticketService.viewTicket(session.getAttribute("loggedInUser")), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
	}

	@GetMapping("viewpid/{pid}")
	public ResponseEntity<List> viewTicketByPid(@PathVariable int pid) throws Exception {
		HttpSession session = UserController.httpsession;
		if (session != null) {
			return new ResponseEntity<List>(ticketService.viewTicketByPid(pid), HttpStatus.OK);
		} else {
			// Handle unauthorized access
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


}