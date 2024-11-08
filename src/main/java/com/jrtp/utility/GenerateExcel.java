package com.jrtp.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jrtp.entity.CitizenPlanInfo;
import com.jrtp.repo.CitizenPlanRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
@Component
public class GenerateExcel {
	
//     public void generate(HttpServletResponse responce,List<CitizenPlanInfo>plans ) throws IOException {
//    	 Workbook workbook = new HSSFWorkbook();
// 		Sheet sheet = workbook.createSheet("Citizen plan info");
// 		Row headerRow = sheet.createRow(0);
// 		headerRow.createCell(0).setCellValue("ID");
// 		headerRow.createCell(1).setCellValue("Citizen Name");
// 		headerRow.createCell(2).setCellValue("plan Name");
// 		headerRow.createCell(3).setCellValue("plan Status");
// 		headerRow.createCell(4).setCellValue("Gender");
// 		headerRow.createCell(5).setCellValue("Start Date");
// 		headerRow.createCell(6).setCellValue("End Date");
// 		headerRow.createCell(7).setCellValue("Beneficial Amount");
//
// 		
// 		int RowNum = 1;
// 		for (CitizenPlanInfo plan : plans) {
// 			Row dataRow = sheet.createRow(RowNum);
// 			dataRow.createCell(0).setCellValue(plan.getCitizenid());
// 			dataRow.createCell(1).setCellValue(plan.getCitizenname());
// 			dataRow.createCell(2).setCellValue(plan.getPlanname());
// 			dataRow.createCell(3).setCellValue(plan.getPlanstatus());
// 			dataRow.createCell(4).setCellValue(plan.getGender());
// 			dataRow.createCell(5).setCellValue(plan.getPlanstartdate());
// 			dataRow.createCell(6).setCellValue(plan.getPlanenddate());
// 			if (plan.getBeneficialamount() != null) {
// 				dataRow.createCell(7).setCellValue(plan.getBeneficialamount());
// 			} else {
// 				dataRow.createCell(7).setCellValue("N.A");
// 			}
// 			RowNum++;
// 		}
// 		ServletOutputStream out = responce.getOutputStream();
// 		workbook.write(out);
// 		workbook.close();
//     }
    public void generate(ByteArrayOutputStream outputstream,List<CitizenPlanInfo>plans) throws IOException {
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
  		ByteArrayOutputStream out = outputstream;
  		workbook.write(out);
  		workbook.close();
    }
}
