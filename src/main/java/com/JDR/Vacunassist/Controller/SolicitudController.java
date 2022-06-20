package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.SolicitudDTO;
import com.JDR.Vacunassist.Service.SolicitudService;

@RestController
@CrossOrigin
public class SolicitudController {

	@Autowired
	SolicitudService solicitudService;
	
	@GetMapping("/getSolicitudes")
	public List<SolicitudDTO> getSolicitudes(){
		return solicitudService.getSolicitudes();
	}
}
