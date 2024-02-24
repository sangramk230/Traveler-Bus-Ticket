package com.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entity.AdminViewDetails;
import com.bus.entity.Ticket;
import com.bus.entity.User;
import com.bus.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private HttpServletRequest request;

	static HttpSession httpsession;

	@RequestMapping("admin/{email}/{password}")
	public ResponseEntity<Boolean> validate(@PathVariable String email, @PathVariable String password) {
		System.out.println(email);
		boolean answer = adminService.login(email, password);
		System.out.println(answer);

		if (answer) {
			request.getSession().setAttribute("loggedInAdmin", email);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);

		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

	}

	@GetMapping("viewuser")
	public ResponseEntity<List<User>> viewUser() {
		List<User> users = adminService.viewUser();
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		if (users != null && session != null) {
			return ResponseEntity.ok(users);

		} else {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("adminview/{id}")
	public ResponseEntity<List<AdminViewDetails>> adminView(@PathVariable int id) {
		System.out.println();
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		if (session != null) {
			return new ResponseEntity<List<AdminViewDetails>>(adminService.adminView(id), HttpStatus.OK);

		} else {
			return new ResponseEntity<List<AdminViewDetails>>(HttpStatus.BAD_REQUEST);

		}
	}

	// Admin endpoint to view pending tickets
	@GetMapping("pending-tickets")
	public ResponseEntity<List<Ticket>> getPendingTickets() {
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		if (session != null) {
			return new ResponseEntity<>(adminService.getPendingTickets(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}

	// Admin endpoint to approve or reject a ticket
	@PutMapping("approve-ticket/{pid}")
	public ResponseEntity<String> approveTicket(@PathVariable int pid) {
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		boolean updated = adminService.updateTicketStatus(pid, "Approved");
		if (updated && session != null) {
			return new ResponseEntity<>("Ticket approved successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to approve ticket", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("reject-ticket/{pid}")
	public ResponseEntity<String> rejectTicket(@PathVariable int pid) {
		System.out.println(pid);
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		boolean updated = adminService.updateTicketStatus(pid, "Rejected");
		if (updated && session != null) {
			return new ResponseEntity<>("Ticket rejected successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to reject ticket", HttpStatus.NOT_FOUND);
		}
	}

}
