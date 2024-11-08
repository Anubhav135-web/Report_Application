package com.jrtp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.request.SearchRequest;
import com.jrtp.service.PlanService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PlanController {
	@Autowired
	private PlanService service;
	

	@GetMapping("/")
	public String indexPages(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "report";

	}

	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model) {
		List<CitizenPlanInfo> plans = service.search(request);
		model.addAttribute("plans", plans);
		init(model);
		System.out.println("SearchRequest: " + request); // Log the request to see the input
		System.out.println("Number of plans found: " + plans.size());
		plans.forEach(plan -> System.out.println(plan));
		return "report";
		
	}

	@GetMapping("/Excel")
	public void getExcel(HttpServletResponse responce1) throws Exception {
		responce1.setContentType("application/octet-stream");
		responce1.addHeader("Content-Disposition","attachment;filename=plans.xls");
		service.exportExcel(responce1);

	}
	@GetMapping("/pdf")
	public void getPDF(HttpServletResponse responce1) throws Exception {
		responce1.setContentType("application/pdf");
		responce1.addHeader("Content-Disposition","attachment;filename=plans.pdf");
		service.exportPDF(responce1);

	}
	public void init(Model model) {

		model.addAttribute("planname", service.getPlanName());
		model.addAttribute("planstatus", service.getPlanStatus());

	}
}
