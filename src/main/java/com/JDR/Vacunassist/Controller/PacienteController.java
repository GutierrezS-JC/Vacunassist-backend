package com.JDR.Vacunassist.Controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.PacienteRequest;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunadorRequest;
import com.JDR.Vacunassist.Service.PacienteService;

@RestController
@CrossOrigin
public class PacienteController {

	@Autowired
	PacienteService pacienteService;
	
	@GetMapping("/getDnisPacientes")
	public List<Integer> getDnisPacientes(){
		return pacienteService.getDnisPacientes();
	}
	
	@GetMapping("/getEmailsPacientes")
	public List<String> getEmailsPacientes(){
		return pacienteService.getEmailsPacientes();
	}
	
	@PostMapping("/cargarPaciente")
	public ResponseEntity<Integer> cargarPaciente(@RequestBody PacienteRequest pacienteRequest ){
		return ResponseEntity.ok(pacienteService.cargarPaciente(pacienteRequest));
//		System.out.println(LocalDate.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).getYear() - LocalDate.ofInstant(pacienteRequest.getFechaNacimiento().toInstant(), ZoneId.systemDefault()).getYear());
		
//		System.out.println(convertirALocalDate(new Date()).getYear() - pacienteRequest.getFechaNacimiento().getYear());
//		System.out.println(pacienteRequest.getFechaNacimiento());
		
//		System.out.println(convertirALocalDate(pacienteRequest.getFechaNacimiento()));
//		System.out.println(convertirALocalDate(new Date()));
//		final LocalDate firstDate = LocalDate.parse(new Date().toString(), formatter);
//		System.out.println(firstDate);
//        final LocalDate secondDate = LocalDate.parse(pacienteRequest.getFechaNacimiento().toString(), formatter);
//        System.out.println(secondDate);
//        long days = ChronoUnit.DAYS.between(pacienteRequest.getFechaNacimiento().GET, convertirALocalDate(new Date()));
//        System.out.println("Days between: " + days);
//        System.out.println(pacienteRequest.getFechaNacimiento());
//		return null;

	}
	
}
