package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.AdministradorDTO;
import com.JDR.Vacunassist.Dto.VacunatorioDTO;
import com.JDR.Vacunassist.Dto.VacunatorioFullDTO;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Service.VacunatorioService;

@RestController
public class VacunatorioController {

	@Autowired
	VacunatorioService vacunatorioService;
	
	@GetMapping("/getVacunatorios")
	public List<VacunatorioFullDTO> getVacunatorios(){
		return vacunatorioService.devolverVacunatorios();
	}
	
	@GetMapping("/getVacunatorio/{id}")
	public ResponseEntity<VacunatorioFullDTO> getVacunatorioPorId(@PathVariable(name="id") Integer id) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunatorioService.devolverVacunatorioPorId(id));
	}
	
	@GetMapping("/getVacunatorioPorNombre")
	public ResponseEntity<VacunatorioFullDTO> getVacunatorioPorNombre(@RequestParam("nombre") String nombre) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunatorioService.devolverVacunatorioPorNombre(nombre));
	}

}
