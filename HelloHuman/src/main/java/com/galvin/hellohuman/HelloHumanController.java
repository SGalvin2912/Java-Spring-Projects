package com.galvin.hellohuman;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloHumanController {


	
	@RequestMapping("/")
	public String searchForSomething(@RequestParam(value="name", required=false) String searchTerm) {
		System.out.println("*******");
		System.out.println(searchTerm);
		System.out.println("*******");
		if(searchTerm == null) {
			return "Hello Human!" + " Welcome to SpringBoot";
		}else {
			return "Hello " + searchTerm + " Welcome to SpringBoot";
		}
	}
	
	
	
}
