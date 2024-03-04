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
import com.bus.entity.User;
import com.bus.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/admin/")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private HttpServletRequest request;

	static HttpSession httpsession;

	@GetMapping("adminlogin/{email}/{password}")
	public ResponseEntity<Boolean> validate(@PathVariable String email, @PathVariable String password)
			throws Exception {
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
	public ResponseEntity<List<User>> viewUser() throws Exception {
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
	public ResponseEntity<List<AdminViewDetails>> adminView(@PathVariable int id) throws Exception {
		System.out.println();
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		if (session != null) {
			return new ResponseEntity<List<AdminViewDetails>>(adminService.adminView(id), HttpStatus.OK);

		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("pending-tickets")
	public ResponseEntity<List<AdminViewDetails>> getPendingTickets() throws Exception {
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		if (session != null) {
			List<AdminViewDetails> bb = adminService.getPendingTickets();
					if (bb != null) {
						return new ResponseEntity<>(bb, HttpStatus.OK);

					} else {
						return new ResponseEntity<>( HttpStatus.OK);
					}

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	@PutMapping("approve-ticket/{pid}")
	public ResponseEntity<Boolean> approveTicket(@PathVariable int pid) throws Exception {
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		boolean updated = adminService.updateTicketStatus(pid, "Approved");
		if (updated && session != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("reject-ticket/{pid}")
	public ResponseEntity<Boolean> rejectTicket(@PathVariable int pid) throws Exception {
		System.out.println(pid);
		HttpSession session = request.getSession();
		session.getAttribute("loggedInAdmin");
		boolean updated = adminService.updateTicketStatus(pid, "Rejected");
		if (updated && session != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

}
