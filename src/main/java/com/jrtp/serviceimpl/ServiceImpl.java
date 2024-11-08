package com.jrtp.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.repo.CitizenPlanRepo;
import com.jrtp.request.SearchRequest;
import com.jrtp.service.PlanService;
import com.jrtp.utility.EmailSender;
import com.jrtp.utility.GenerateExcel;
import com.jrtp.utility.GeneratePDF;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

@Service
public class ServiceImpl implements PlanService {
	@Autowired
	private CitizenPlanRepo repo;
	@Autowired
	private GenerateExcel  generateexcel;
	@Autowired
	private GeneratePDF generatepdf;
	@Autowired
	private EmailSender emailsender;
	@Override
	public List<String> getPlanName() {
		return repo.getPlanName();

	}

	@Override
	public List<String> getPlanStatus() {
		return repo.getPlanStatus();

	}

	@Override
	public List<CitizenPlanInfo> search(SearchRequest request) {
		// Create a base query entity
		CitizenPlanInfo entity = new CitizenPlanInfo();

		if (request.getPlanname() != null && !request.getPlanname().isEmpty()) {
			entity.setPlanname(request.getPlanname());
		}
		if (request.getPlanstatus() != null && !request.getPlanstatus().isEmpty()) {
			entity.setPlanstatus(request.getPlanstatus());
		}
		if (request.getGender() != null && !request.getGender().isEmpty()) {
			entity.setGender(request.getGender());
		}
		if (request.getPlanstartdate() != null) {
			entity.setPlanstartdate(request.getPlanstartdate());
		}
		if (request.getPlanenddate() != null) {
			entity.setPlanenddate(request.getPlanenddate());
		}

		System.out.println("Search Entity: " + entity);

		// Create ExampleMatcher that ignores null values and supports partial string
		// matches
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Allow substring matches
				.withIgnoreCase(); // Case insensitive matching

		// Create the Example object based on the populated entity
		Example<CitizenPlanInfo> example = Example.of(entity, matcher);

		// Use the repository to find all matching records
		List<CitizenPlanInfo> results = repo.findAll(example);

		// Log the results for debugging
		System.out.println("Results Size: " + results.size());
		results.forEach(result -> System.out.println(result));

		return results;
	}

	@Override
	public void exportExcel(HttpServletResponse responce ) throws Exception {
		List<CitizenPlanInfo> plans = repo.findAll();

        // Generate the Excel file in a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        generateexcel.generate(byteArrayOutputStream, plans);

        // Send the file as an email attachment
        emailsender.sendMail("Report plan info", byteArrayOutputStream, "plans.xls");

        // Write the same ByteArrayOutputStream to the HTTP response for download
        responce.setContentType("application/octet-stream");
        responce.setHeader("Content-Disposition", "attachment;filename=plans.xls");
        byteArrayOutputStream.writeTo(responce.getOutputStream());
        responce.flushBuffer();
    }

	

	@Override
	public void exportPDF(HttpServletResponse responce) throws Exception {
      List<CitizenPlanInfo>plans=repo.findAll();
      ByteArrayOutputStream out=new ByteArrayOutputStream();
      generatepdf.generate(out, plans);
      emailsender.sendMail("PDG", out, "plans.pdf");
      responce.setContentType("application/pdf");
      responce.setHeader("Content-Disposition","attachment;filename=plans.pdf");
      out.writeTo(responce.getOutputStream());
      responce.flushBuffer();
      
      
	}
	
    

}
