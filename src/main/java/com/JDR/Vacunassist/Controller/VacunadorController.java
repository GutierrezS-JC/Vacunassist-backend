package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Service.VacunadorService;

@RestController
public class VacunadorController {
	
	@Autowired 
	VacunadorService vacunadorService;
	
	@GetMapping("/getVacunadores")
	public List<VacunadorDTO> getVacunadores(){
		return vacunadorService.devolverVacunadores();
	}
	
	@GetMapping("/getVacunadoresEnRango")
	public List<VacunadorDTO> getVacunadoresEnRango(@RequestParam("inferiorDni") Integer inferiorDni, @RequestParam("superiorDni") Integer superiorDni){
		return vacunadorService.devolverVacunadoresEnRango(inferiorDni, superiorDni);
	}
	
	@GetMapping("/getVacunador/{id}")
	public ResponseEntity<VacunadorDTO> getVacunadorPorId(@PathVariable(name="id") Integer id) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.devolverVacunadorPorId(id));
	}
	
	@GetMapping("/getVacunadorByDni/{dni}")
	public ResponseEntity<VacunadorDTO> getVacunadorPorDni(@PathVariable(name="dni") Integer dni) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.devolverVacunadorPorDni(dni));
	}
	
}
