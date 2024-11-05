package com.jrtp.serviceimpl;

import java.io.File;
import java.io.IOException;
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

@Service
public class ServiceImpl implements PlanService {
	@Autowired
	private CitizenPlanRepo repo;

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
	public void exportExcel(HttpServletResponse responce) throws Exception {

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Citizen plan info");
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("plan Name");
		headerRow.createCell(3).setCellValue("plan Status");
		headerRow.createCell(4).setCellValue("Gender");
		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.createCell(6).setCellValue("End Date");
		headerRow.createCell(7).setCellValue("Beneficial Amount");

		List<CitizenPlanInfo> plans = repo.findAll();
		int RowNum = 1;
		for (CitizenPlanInfo plan : plans) {
			Row dataRow = sheet.createRow(RowNum);
			dataRow.createCell(0).setCellValue(plan.getCitizenid());
			dataRow.createCell(1).setCellValue(plan.getCitizenname());
			dataRow.createCell(2).setCellValue(plan.getPlanname());
			dataRow.createCell(3).setCellValue(plan.getPlanstatus());
			dataRow.createCell(4).setCellValue(plan.getGender());
			dataRow.createCell(5).setCellValue(plan.getPlanstartdate());
			dataRow.createCell(6).setCellValue(plan.getPlanenddate());
			if (plan.getBeneficialamount() != null) {
				dataRow.createCell(7).setCellValue(plan.getBeneficialamount());
			} else {
				dataRow.createCell(7).setCellValue("N.A");
			}
			RowNum++;
		}
		ServletOutputStream out = responce.getOutputStream();
		workbook.write(out);
		workbook.close();

	}

	@Override
	public void exportPDF(HttpServletResponse responce) throws Exception {
      Document document=new Document(PageSize.A4);
      Font title=FontFactory.getFont(FontFactory.TIMES_ROMAN);
      title.setSize(20);
      PdfWriter.getInstance(document, responce.getOutputStream());
      document.open();
      Paragraph p=new Paragraph("I Love You :::Prinshu Darling",title);
      p.setAlignment(Paragraph.ALIGN_CENTER);
      document.add(p);
      PdfPTable table=new PdfPTable(6);
      table.setSpacingBefore(5);
      table.addCell("ID");
      table.addCell("CitizenName");
      table.addCell("PlanName");
      table.addCell("PlanStatus");
      table.addCell("StartDate");
      table.addCell("EndDate");
      
      
      List<CitizenPlanInfo>plans=repo.findAll();
      for(CitizenPlanInfo plan: plans) {
    	  table.addCell(String.valueOf(plan.getCitizenid()));
    	  table.addCell(plan.getCitizenname());
    	  table.addCell(plan.getPlanstatus());
    	  table.addCell(plan.getPlanstartdate()+"");
    	  table.addCell(plan.getPlanenddate()+"");
    	
      }
      document.add(table);
      document.close();
	}

}
