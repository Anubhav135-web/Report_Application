package com.jrtp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {
	@GetMapping("/addenquiry")
	public String addEnquiry() {
		return "add-enquiry";
	}

}
