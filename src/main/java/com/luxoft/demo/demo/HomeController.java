package com.luxoft.demo.demo;

import java.time.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		LocalDate localDate = LocalDate.now();
		
		return "Welcome to the Luxoft Demo </br> " + localDate;
	}
}