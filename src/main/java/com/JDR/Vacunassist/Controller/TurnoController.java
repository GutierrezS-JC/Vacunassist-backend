package com.JDR.Vacunassist.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.CargarTurno;
import com.JDR.Vacunassist.Dto.GenerarListadoRequest;
import com.JDR.Vacunassist.Dto.MetricasResponse;
import com.JDR.Vacunassist.Dto.ReporteResponse;
import com.JDR.Vacunassist.Dto.ReporteTotalResponse;
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
	
	// LISTADO
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
	// FIN LISTADO
	
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
	 
	@GetMapping("/getReporteCovid")
	public ReporteResponse getReporteCovid() throws ResourceNotFoundException{
		return turnoService.getReporteCovid();
	}
	
	@GetMapping("/getReporteCovidEnRango") // Chart
	public ReporteResponse getReporteCovidEnRango(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) throws ResourceNotFoundException{
		return turnoService.getReporteCovidEnRango(fechaInicio, fechaFin);
	}
	
	@GetMapping("/getReporteGripe")
	public ReporteResponse getReporteGripe() throws ResourceNotFoundException{
		return turnoService.getReporteGripe();
	}
	
	@GetMapping("/getReporteGripeEnRango") // Chart
	public ReporteResponse getReporteGripeEnRango(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) throws ResourceNotFoundException{
		return turnoService.getReporteGripeEnRango(fechaInicio, fechaFin);
	}
	
	@GetMapping("/getReporteYellow")
	public ReporteResponse getReporteYellow() throws ResourceNotFoundException{
		return turnoService.getReporteYellow();
	}
	
	@GetMapping("/getReporteYellowEnRango") // Chart
	public ReporteResponse getReporteYellowEnRango(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) throws ResourceNotFoundException{
		return turnoService.getReporteYellowEnRango(fechaInicio, fechaFin);
	}
		
	@PostMapping("/generarListadoReporte")
	public List<TurnoResponse> generarListadoReporte(@RequestBody GenerarListadoRequest request) throws ResourceNotFoundException{
		return turnoService.generarListadoReporte(request);
	}
	
	@GetMapping("/getTurnosTotal") 
	public ReporteResponse getTurnosTotal() throws ResourceNotFoundException{
		return turnoService.getTurnosTotal();
	}
	
	@GetMapping("/getTurnosTotalEnRango") 
	public ReporteResponse getTurnosTotalEnRango(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) throws ResourceNotFoundException{
		return turnoService.getTurnosTotalEnRango(fechaInicio, fechaFin);
	}
	
	@GetMapping("/getTurnosTotalPorVacunatorio") 
	public List<ReporteTotalResponse> getTurnosTotalPorVacunatorio() throws ResourceNotFoundException{
		return turnoService.getTurnosTotalPorVacunatorio();
	}
	
	@GetMapping("/getTurnosTotalPorVacunatorioEnRango") 
	public List<ReporteTotalResponse> getTurnosTotalPorVacunatorioEnRango(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) throws ResourceNotFoundException{
		return turnoService.getTurnosTotalPorVacunatorioEnRango(fechaInicio, fechaFin);
	}
	// FIN METRICAS 
	
	@GetMapping("/getTurnosFuturosPorDni")
	public List<TurnosPendientesPacienteResponse> getTurnosFuturosPorDni(@RequestParam("dni") Integer dni) throws ResourceNotFoundException{
		return turnoService.getTurnosFuturosPorDni(dni);
	}
	
	// FIEBRE AMARILLA
	@PostMapping("/asignarTurnoFiebreAmarilla")
	public Boolean asignarTurnoFiebreAmarilla(@RequestBody CargarTurno turnoRequest) throws ResourceNotFoundException{
		return turnoService.asignarTurnoFiebreAmarilla(turnoRequest);
	}
	
	@DeleteMapping("/eliminarTurnoFiebreAmarilla")
	public Boolean eliminarTurnoFiebreAmarilla(@RequestParam("turnoId") Integer turnoId) throws ResourceNotFoundException{
		return turnoService.eliminarTurnoFiebreAmarilla(turnoId);
	}
}
