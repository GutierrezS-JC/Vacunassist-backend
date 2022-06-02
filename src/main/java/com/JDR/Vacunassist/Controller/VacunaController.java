package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.VacunaPost;
import com.JDR.Vacunassist.Service.VacunaService;

@RestController
@CrossOrigin
public class VacunaController {

	@Autowired
	VacunaService vacunaService;
	
	@GetMapping("/getVacunas")
	public List<VacunaPost> getVaunas(){
		return vacunaService.devolverVacunas();
	}
	
	@PostMapping("/cargarVacuna")
	public ResponseEntity<VacunaPost> cargarVacuna(@RequestBody VacunaPost vacunaPost ){
		return ResponseEntity.ok(vacunaService.cargarVacuna(vacunaPost));
	}
}
