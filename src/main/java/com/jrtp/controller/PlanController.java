package com.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jrtp.service.PlanService;


@Controller
public class PlanController {
@Autowired
private PlanService service;
@GetMapping("/")
public String indexPages() {
	return "report";
}
}
