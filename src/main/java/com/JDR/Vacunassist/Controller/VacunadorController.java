package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Service.VacunadorService;

@RestController
public class VacunadorController {
	
	@Autowired 
	VacunadorService vacunadorService;
	
	@GetMapping("/getVacunadores")
	public List<VacunadorDTO> getVacunadores(){
		return vacunadorService.devolverVacunadores();
	}

}
