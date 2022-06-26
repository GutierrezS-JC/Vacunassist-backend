package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.TurnoResponse;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Service.TurnoService;

@RestController
@CrossOrigin
public class TurnoController {

	@Autowired
	TurnoService turnoService;
	
	@GetMapping("/getAllTurnos")
	public List<TurnoResponse> getAllTurnos() throws ResourceNotFoundException{
		return turnoService.getAllTurnos();
	}
	
	@GetMapping("/getTurnosCovid")
	public List<TurnoResponse> getTurnosCovid() throws ResourceNotFoundException{
		return turnoService.getTurnosCovid();
	}
	
	@GetMapping("/getTurnosGripe")
	public List<TurnoResponse> getTurnosGripe() throws ResourceNotFoundException{
		return turnoService.getTurnosGripe();
	}
	
	@GetMapping("/getTurnosYellow")
	public List<TurnoResponse> getTurnosYellow() throws ResourceNotFoundException{
		return turnoService.getTurnosYellow();
	}
}
