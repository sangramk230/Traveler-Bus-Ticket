package com.bus.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entity.Checkbus;
import com.bus.service.CheckBusService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/checkbus/")
public class CheckBusController {

	@Autowired
	CheckBusService checkBusService;

	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping("check/{from}/{to}/{bustype}")
	public ResponseEntity<List<Checkbus>> isAvailable(@PathVariable String from, @PathVariable String to,
			@PathVariable String bustype) throws Exception {
		HttpSession session = UserController.httpsession;
		List<Checkbus> bb = checkBusService.isSeatAvailable(from, to, bustype);
		System.out.println("Controller : " + to + " - " + from + " " + bustype);
		if (session != null && bb != null) {

			return new ResponseEntity<List<Checkbus>>(bb, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Checkbus>>(HttpStatus.OK);

		}
	}

	@GetMapping("allbus")
	public ResponseEntity<List<Checkbus>> allBus() throws Exception {
		HttpSession session = UserController.httpsession;
		HttpSession session1 = AdminController.httpsession;
		List<Checkbus> bb = checkBusService.allBus();
		if (session != null && bb != null || session1 != null) {

			return new ResponseEntity<List<Checkbus>>(bb, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Checkbus>>(HttpStatus.OK);

		}
	}

	@PostMapping("addbus")
	public boolean addBus(@RequestBody Checkbus checkbus) {
		HttpSession session = AdminController.httpsession;
		boolean dd = checkBusService.addBus(checkbus);
		System.out.println("cnn --" + checkbus + " -" + "boole" + dd);
		if (session != null) {
			if (dd) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
