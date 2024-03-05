package com.bus.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entity.User;
import com.bus.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/")
public class UserController {
	@Autowired
	private HttpServletRequest request;


	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserService userService;
	static HttpSession httpsession;

	@RequestMapping("signup")
	private void signUp(@RequestBody User user) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(user);
		session.getTransaction().commit();
		session.close();
	}

	@GetMapping("login/{email}/{password}")
	public ResponseEntity<Boolean> loginUser(@PathVariable String email, @PathVariable String password)
			throws Exception {
		httpsession = request.getSession();
		boolean isAuthenticated = userService.loginUser(email, password);
		if (isAuthenticated) {
			request.getSession().setAttribute("loggedInUser", email);
			System.out.println("rrr" + httpsession);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("profile")
	public ResponseEntity<List<User>> profile() throws Exception {
		HttpSession userSession = UserController.httpsession;
		List<User> bb = userService.profile();
		System.out.println("frttttttttt" + bb);
		if (userSession != null && bb != null) {
				userSession.getAttribute("loggedInUser");
				System.out.println(userSession);
				System.out.println(bb);
				return new ResponseEntity<>(bb, HttpStatus.OK);

			} else {
			return new ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED);
		}
	}

}
