package com.jrtp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrtp.binding.EnquiryForm;
import com.jrtp.binding.EnquirySearchCriteria;
import com.jrtp.entity.StudentEnqEntity;
import com.jrtp.service.EnquiryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	EnquiryService enquiryservice;
	@Autowired
	HttpSession session;

	@GetMapping("/addenquiry")
	public String getAddEnquiryPage(Model model) {
		// Fetching data for dropdowns
		List<String> courses = enquiryservice.getCourseName();
		List<String> status = enquiryservice.getEnqStatus();

		// Debugging: Log to ensure data is fetched
		System.out.println("Courses: " + courses);
		System.out.println("Statuses: " + status);

		// Prepare the form and add data to the model
		EnquiryForm formObj = new EnquiryForm();
		model.addAttribute("formObj", formObj);
		model.addAttribute("courses", courses);
		model.addAttribute("statuses", status);

		return "add-enquiry";
	}

	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {
		String status = enquiryservice.addEnquiry(formObj);
		if (status.equalsIgnoreCase("Enquiry Saved Successfully")) {
			model.addAttribute("succMsg", "Enquiry added successfully");
		} else {
			model.addAttribute("errMsg", "Problem occurred");
		}

		return "add-enquiry";

	}

	public void initForm(Model model) {
		List<String> courses = enquiryservice.getCourseName();
		List<String> status = enquiryservice.getEnqStatus();

		// Debugging: Log to ensure data is fetched
		System.out.println("Courses: " + courses);
		System.out.println("Statuses: " + status);

		// Prepare the form and add data to the model
		EnquiryForm formObj = new EnquiryForm();
		model.addAttribute("formObj", formObj);
		model.addAttribute("courses", courses);
		model.addAttribute("statuses", status);
	}
    @GetMapping("/enquiries")
	public String viewEnquiry( Model model) {
      initForm(model);
      model.addAttribute("searchForm",new EnquirySearchCriteria());
      List<StudentEnqEntity>enquiry=enquiryservice.getEnquiries();
      model.addAttribute("Enquiries", enquiry);
		return "view-enquiries";

	}
    @GetMapping("/filtered-enquiries")
    public String viewFilteredEnquiry(@RequestParam(required = false) String course,
                                      @RequestParam(required = false) String status,
                                      @RequestParam(required = false) String mode,
                                      Model model) {
        // Initialize the criteria
        EnquirySearchCriteria criteria = new EnquirySearchCriteria();
        criteria.setCourse(course);
        criteria.setStatus(status);
        criteria.setMode(mode);

        // Fetch filtered enquiries
        List<StudentEnqEntity> filteredEnquiries = enquiryservice.getfilteredenquiries(criteria);

        // Add attributes to the model
        initForm(model); // Add course, status, mode options
        model.addAttribute("searchForm", criteria); // Pass the current search criteria
        model.addAttribute("Enquiries", filteredEnquiries); // Pass filtered enquiries
         System.out.println(criteria);
        return "filter-result";
    }
    @GetMapping("/edit-enquiry")
    public String editEnquiry(@RequestParam("id") Integer enquiryId, Model model) {
        // Fetch the enquiry details using the ID
        EnquiryForm enquiryForm = enquiryservice.getEnquiryById(enquiryId);

        // Populate the form object with the fetched details
        model.addAttribute("formObj", enquiryForm);

        // Fetch dropdown data
        List<String> courses = enquiryservice.getCourseName();
        List<String> status = enquiryservice.getEnqStatus();

        // Add dropdown data to the model
        model.addAttribute("courses", courses);
        model.addAttribute("statuses", status);

        // Redirect to the Add Enquiry page
        return "add-enquiry";
    }


}
