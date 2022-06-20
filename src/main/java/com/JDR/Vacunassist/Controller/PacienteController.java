package com.JDR.Vacunassist.Controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

import com.JDR.Vacunassist.Dto.PacienteDTO;
import com.JDR.Vacunassist.Dto.PacienteRequest;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunadorRequest;
import com.JDR.Vacunassist.Repository.PacienteRepository;
import com.JDR.Vacunassist.Service.PacienteService;

@RestController
@CrossOrigin
public class PacienteController {

	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@GetMapping("/getPacientes")
	public List<PacienteDTO> getPacientes(){
		return pacienteService.getPacientes();
	}
	
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
		
		
		// AGREGAMOS MINUTOS A FECHA DE BD //
//		String fecha = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//		String fecha2 = LocalDateTime.of(LocalDate.now(), LocalTime.now()).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//		Object response = pacienteRepository.getUltimoTurnoEnFecha("2022-02-21");
//		System.out.println(fecha);
//		System.out.println("Fecha 2: " + fecha2);
//		System.out.println(hora);
//		System.out.println();
//		LocalDateTime fechaNull = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//		System.out.println("Respuesta BD: " + (response == null ? LocalDateTime.of(fechaNull.getYear(), fechaNull.getMonth(), fechaNull.getDayOfMonth(), 10, 0)  : response.toString()));
//		
//		LocalDateTime fechaPlus = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//		System.out.println("FechaPlus Antes: " + fechaPlus.toString());
//		fechaPlus = LocalDateTime.of(fechaPlus.getYear(), fechaPlus.getMonth(), fechaPlus.getDayOfMonth(), fechaPlus.getHour(), (fechaPlus.getMinute() + 10));
//		System.out.println("FechaPlus Despues: " + fechaPlus.toString());
//		return null;
	}
	
}
