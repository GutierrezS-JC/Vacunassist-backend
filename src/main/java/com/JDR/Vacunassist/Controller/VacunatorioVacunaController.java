package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.VacunatorioVacunaDTO;
import com.JDR.Vacunassist.Service.VacunatorioVacunaService;

@RestController
public class VacunatorioVacunaController {

	@Autowired
	VacunatorioVacunaService vacunatorioVacunaService;
	
	//Stock
	@GetMapping("/getAllVacunatoriosVacunas")
	public List<VacunatorioVacunaDTO> getAllVacunatoriosVacunas(){
		return vacunatorioVacunaService.devolverAllVacunatoriosVacunas();
	}
	
	//Stock para 1 vacunatorio
	@GetMapping("/getVacunatorioVacunas")
	public VacunatorioVacunaDTO getVacunatorioVacunas(@RequestParam("vacunatorioId") Integer vacunatorioId){
		return vacunatorioVacunaService.devolverUnVacunatorioVacunas(vacunatorioId);
	}
	
	
	
}
