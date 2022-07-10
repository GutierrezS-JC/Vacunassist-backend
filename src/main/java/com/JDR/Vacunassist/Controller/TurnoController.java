package com.JDR.Vacunassist.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.CargarTurno;
import com.JDR.Vacunassist.Dto.MetricasResponse;
import com.JDR.Vacunassist.Dto.ReporteResponse;
import com.JDR.Vacunassist.Dto.TurnoResponse;
import com.JDR.Vacunassist.Dto.TurnosPendientesPacienteResponse;
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
	
	@GetMapping("/getTurnosDia")
	public List<TurnoResponse> getTurnosDia() throws ResourceNotFoundException{
		return turnoService.getTurnosDia();
	}
	
	@GetMapping("/getTurnosDiaVacunatorio")
	public List<TurnoResponse> getTurnosDiaVacunatorio(@RequestParam("vacunatorioId") Integer vacunatorioId) throws ResourceNotFoundException{
		return turnoService.getTurnosDiaVacunatorio(vacunatorioId);
	}
	
	@GetMapping("/getTurnosAsignadosDespuesDeHoy")
	public List<String> getTurnosAsignadosDespuesDeHoy() throws ResourceNotFoundException{
		return turnoService.getTurnosAsignadosDespuesDeHoy();
	}
	
	@GetMapping("/getTurnosHorasAsignadosEnFecha")
	public List<String> getTurnosHorasAsignadosEnFecha(@RequestParam("fecha") String fecha) throws ResourceNotFoundException{
		return turnoService.getTurnosHorasAsignadosEnFecha(fecha);
	}
	
	// METRICAS 
	
	@GetMapping("/getTurnosPendientesGripe")
	public List<MetricasResponse> getTurnosPendientesGripe() throws ResourceNotFoundException{
		return turnoService.getTurnosPendientesGripe();
	}
	
	@GetMapping("/getTurnosPendientesTodasPorVacunatorio") //Por vacunatorio
	public List<MetricasResponse> getTurnosPendientesTodasPorVacunatorio() throws ResourceNotFoundException{
		return turnoService.getTurnosPendientesTodasPorVacunatorio();
	}
	
	@GetMapping("/getTurnosPendientesTodas") //En todos los vacunatorios
	public List<MetricasResponse> getTurnosPendientesTodas() throws ResourceNotFoundException{
		return turnoService.getTurnosPendientesTodas();
	}
	
	//THIS 
	@GetMapping("/getReporteCovid")
	public ReporteResponse getReporteCovid() throws ResourceNotFoundException{
		return turnoService.getReporteCovid();
	}
	
	@GetMapping("/getReporteGripe")
	public ReporteResponse getReporteGripe() throws ResourceNotFoundException{
		return turnoService.getReporteGripe();
	}
	
	@GetMapping("/getReporteYellow")
	public ReporteResponse getReporteYellow() throws ResourceNotFoundException{
		return turnoService.getReporteYellow();
	}
	
//	@GetMapping("/getReportePacientesCovid")
//	public List<ReporteResponse> getReportePacientesCovid() throws ResourceNotFoundException{
//		return turnoService.getReporteYellow();
//	}
	
	// FIN METRICAS 
	
	// LISTADO TURNOS FUTUROS
	@GetMapping("/getTurnosFuturosPorDni")
	public List<TurnosPendientesPacienteResponse> getTurnosFuturosPorDni(@RequestParam("dni") Integer dni) throws ResourceNotFoundException{
		return turnoService.getTurnosFuturosPorDni(dni);
	}
	
	@PostMapping("/asignarTurnoFiebreAmarilla")
	public Boolean asignarTurnoFiebreAmarilla(@RequestBody CargarTurno turnoRequest) throws ResourceNotFoundException{
		return turnoService.asignarTurnoFiebreAmarilla(turnoRequest);
	}
	
}
