package com.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		httpsession = request.getSession();

		boolean answer = adminService.login(email, password);
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
		if (users != null && httpsession != null) {
			httpsession.getAttribute("loggedInAdmin");

			return ResponseEntity.ok(users);

		} else {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("adminview/{id}")
	public ResponseEntity<List<AdminViewDetails>> adminView(@PathVariable int id) throws Exception {
		System.out.println();
		if (httpsession != null) {
			httpsession.getAttribute("loggedInAdmin");
			return new ResponseEntity<List<AdminViewDetails>>(adminService.adminView(id), HttpStatus.OK);

		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("pendingtickets")
	public ResponseEntity<List<AdminViewDetails>> getPendingTickets() throws Exception {
		if (httpsession != null) {
			List<AdminViewDetails> bb = adminService.getPendingTickets();
					if (bb != null) {
						httpsession.getAttribute("loggedInAdmin");
						return new ResponseEntity<>(bb, HttpStatus.OK);

					} else {
						return new ResponseEntity<>( HttpStatus.OK);
					}

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}

	@PutMapping("approveticket/{pid}/{phoneno}")
	public ResponseEntity<Boolean> approveTicket(@PathVariable int pid, @PathVariable String phoneno) throws Exception {
		System.out.println(phoneno);

		boolean updated = adminService.updateTicketStatus(pid, phoneno, "Approved");
		if (updated && httpsession != null) {
			httpsession.getAttribute("loggedInAdmin");

			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("rejectticket/{pid}/{phoneno}")
	public ResponseEntity<Boolean> rejectTicket(@PathVariable int pid, @PathVariable String phoneno) throws Exception {
		System.out.println(phoneno);
		boolean updated = adminService.updateTicketStatus(pid, phoneno, "Rejected");
		if (updated && httpsession != null) {
			httpsession.getAttribute("loggedInAdmin");

			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("cancelbus/{busid}")
	public ResponseEntity<Boolean> cancelBus(@PathVariable int busid) throws Exception {
		if (httpsession != null) {
			boolean canceled = adminService.cancelBus(busid);
			if (canceled) {
				httpsession.getAttribute("loggedInAdmin");

				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("profileAdmin")
	public ResponseEntity<List<User>> profile() throws Exception {

		List<User> bb = adminService.profile();
		System.out.println("frttttttttt" + bb);
		if (httpsession != null && bb != null) {
			httpsession.getAttribute("loggedInAdmin");
			System.out.println(httpsession);
			System.out.println(bb);
			return new ResponseEntity<>(bb, HttpStatus.OK);

		} else {
			return new ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED);
		}
	}
}
