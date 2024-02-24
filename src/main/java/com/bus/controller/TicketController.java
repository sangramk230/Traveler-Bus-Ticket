package com.bus.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entity.Ticket;
import com.bus.entity.TicketDetails;
import com.bus.service.CheckBusService;
import com.bus.service.TicketService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
public class TicketController {
	// Inject HttpServletRequest to access HttpSession

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private HttpServletRequest request;


	@Autowired
	private CheckBusService checkBusService;

	@RequestMapping("ticket")
	public ResponseEntity<Boolean> addTicket(@RequestBody Ticket ticket) {
		HttpSession session = UserController.httpsession;
		session.getAttribute("loggedInUser");

		System.out.println(session);
		 boolean bb  = ticketService.addTicket(ticket);
			if (session != null && bb) {
				ticket.setCheckstatus("Pending");
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	@DeleteMapping("cancel/{pid}")
	public ResponseEntity<Boolean> cancelTicket(@PathVariable int pid) throws Exception {
		HttpSession session = UserController.httpsession;
		session.getAttribute("loggedInUser");
		if (session != null) {
			boolean canceled = ticketService.cancelTicket(pid);
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

	@PutMapping("update")
	public ResponseEntity<List<Ticket>> updateTicket(@RequestBody Ticket ticket) {
		HttpSession session = UserController.httpsession;
		session.getAttribute("loggedInUser");

		if (session != null) {
			List<Ticket> updatedTickets = ticketService.updateTicket(ticket);
			return new ResponseEntity<>(updatedTickets, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("view")
	public ResponseEntity<List<TicketDetails>> viewTicket() {
		HttpSession session = UserController.httpsession;
		session.getAttribute("loggedInUser");

		if (session != null) {
			System.out.println(session);
				return new ResponseEntity<List<TicketDetails>>(ticketService.viewTicket(), HttpStatus.OK);
			} else {
				System.out.println("o988s");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

	}


	@GetMapping("viewtt/{pid}")
	public ResponseEntity<List> viewTicketByPid(@PathVariable int pid) {
		ticketService.deleteByInvalid();
		HttpSession session = UserController.httpsession;
		if (session != null) {
			return new ResponseEntity<List>(ticketService.viewTicketByPid(pid), HttpStatus.OK);
		} else {
			// Handle unauthorized access
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


}
