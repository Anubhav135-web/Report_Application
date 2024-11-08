package com.jrtp.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jrtp.entity.CitizenPlanInfo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class GeneratePDF {
	public void generate(ByteArrayOutputStream output,List<CitizenPlanInfo>plans) throws DocumentException, IOException {
		Document document=new Document(PageSize.A4);
	      Font title=FontFactory.getFont(FontFactory.TIMES_ROMAN);
	      title.setSize(20);
	      PdfWriter.getInstance(document, output);
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
