package com.JDR.Vacunassist.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Service.PDFGeneratorService;
import com.lowagie.text.DocumentException;

@RestController
@CrossOrigin
public class PDFExportController {

	@Autowired
	PDFGeneratorService pdfGeneratorService;
	
	@GetMapping("/pdf/generate")
	public void generatePDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Certificado_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		this.pdfGeneratorService.export(response);
	}
}
