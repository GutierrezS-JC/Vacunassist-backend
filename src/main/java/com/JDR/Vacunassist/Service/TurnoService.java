package com.JDR.Vacunassist.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.CargarTurno;
import com.JDR.Vacunassist.Dto.GenerarListadoRequest;
import com.JDR.Vacunassist.Dto.MetricasResponse;
import com.JDR.Vacunassist.Dto.ReporteResponse;
import com.JDR.Vacunassist.Dto.ReporteTotalResponse;
import com.JDR.Vacunassist.Dto.TurnoResponse;
import com.JDR.Vacunassist.Dto.TurnosPendientesPacienteResponse;
import com.JDR.Vacunassist.Model.Administrador;
import com.JDR.Vacunassist.Model.Paciente;
import com.JDR.Vacunassist.Model.Solicitud;
import com.JDR.Vacunassist.Model.Turno;
import com.JDR.Vacunassist.Model.Vacuna;
import com.JDR.Vacunassist.Model.Vacunatorio;
import com.JDR.Vacunassist.Model.Zona;
import com.JDR.Vacunassist.Repository.AdministradorRepository;
import com.JDR.Vacunassist.Repository.PacienteRepository;
import com.JDR.Vacunassist.Repository.SolicitudRepository;
import com.JDR.Vacunassist.Repository.TurnoRepository;
import com.JDR.Vacunassist.Repository.VacunaRepository;
import com.JDR.Vacunassist.Repository.ZonaRepository;

@Service
public class TurnoService {

	@Autowired
	TurnoRepository turnoRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	AdministradorRepository administradorRepository;
	
	@Autowired
	ZonaRepository zonaRepository;
	
	@Autowired
	VacunaRepository vacunaRepository;
	
	@Autowired
	SolicitudRepository solicitudRepository;

	public List<TurnoResponse> getAllTurnos() {
		List<Object[]> listaDb = turnoRepository.getAllTurnos();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}

	public List<TurnoResponse> getTurnosCovid() {
		List<Object[]> listaDb = turnoRepository.getTurnosCovid();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}
	
	public List<TurnoResponse> getTurnosGripe() {
		List<Object[]> listaDb = turnoRepository.getTurnosGripe();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}
	
	public List<TurnoResponse> getTurnosYellow() {
		List<Object[]> listaDb = turnoRepository.getTurnosYellow();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}

	public List<TurnoResponse> getTurnosDia() {
		String fechaHoy = LocalDate.now().toString();
		System.out.println(fechaHoy);
		List<Object[]> listaDb = turnoRepository.getTurnosDia(fechaHoy);
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}
	
	public List<TurnoResponse> getTurnosDiaVacunatorio(Integer vacunatorioId) {
		String fechaHoy = LocalDate.now().toString();
		System.out.println(fechaHoy);
		List<Object[]> listaDb = turnoRepository.getTurnosDiaVacunatorio(fechaHoy,vacunatorioId );
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}

	// REPORTE LISTADO
	public List<TurnoResponse> generarListadoReporte(GenerarListadoRequest request) {
		List<Object[]> listaDb = null;
		
		// SIN VACUNA SETEADA
		if(request.getPacienteDni() == null && request.getVacunatorioId() == null && request.getVacunaId() == null) {
			listaDb = turnoRepository.getReporteSimpleSinVacuna(request.getFechaInicio().toString(), request.getFechaFin().toString());			
		}
		
		else if (request.getPacienteDni() == null && request.getVacunatorioId() != null && request.getVacunaId() == null) {
			listaDb = turnoRepository.getReporteParcialConVacunatorioSinVacuna(request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getVacunatorioId());
		}
	
		else if (request.getPacienteDni() !=null && request.getVacunatorioId() == null && request.getVacunaId() == null) {
			listaDb = turnoRepository.getReporteParcialConDniSinVacuna(request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getPacienteDni());
			
		}
		
		else if(request.getPacienteDni() !=null && request.getVacunatorioId() != null && request.getVacunaId() == null) {
			listaDb = turnoRepository.getReporteCompletoSinVacuna(request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getVacunatorioId(), request.getPacienteDni());	
		}
		
		// CON VACUNA SETEADA
		else if(request.getPacienteDni() == null && request.getVacunatorioId() == null && request.getVacunaId() != null) {
			if(request.getVacunaId() == 1) {
				listaDb = turnoRepository.getReporteSimpleCovid(request.getFechaInicio().toString(), request.getFechaFin().toString());			
			}
			else {				
				listaDb = turnoRepository.getReporteSimple(request.getVacunaId(), request.getFechaInicio().toString(), request.getFechaFin().toString());			
			}
		}
		
		else if (request.getPacienteDni() == null && request.getVacunatorioId() != null && request.getVacunaId() != null) {
			if(request.getVacunaId() == 1) {
				listaDb = turnoRepository.getReporteParcialConVacunatorioCovid(request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getVacunatorioId());				
			}
			else {
				listaDb = turnoRepository.getReporteParcialConVacunatorio(request.getVacunaId(), request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getVacunatorioId());
			}
		}
		
		else if (request.getPacienteDni() !=null && request.getVacunatorioId() == null && request.getVacunaId() != null) {
			if(request.getVacunaId() == 1) {				
				listaDb = turnoRepository.getReporteParcialConDniCovid(request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getPacienteDni());
			}
			else {
				listaDb = turnoRepository.getReporteParcialConDni(request.getVacunaId(), request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getPacienteDni());
			}
		}
		
		else if(request.getPacienteDni() !=null && request.getVacunatorioId() != null && request.getVacunaId() != null) {
			if(request.getVacunaId() == 1) {	
				listaDb = turnoRepository.getReporteCompletoCovid(request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getVacunatorioId(), request.getPacienteDni());
			}
			else {				
				listaDb = turnoRepository.getReporteCompleto(request.getVacunaId(), request.getFechaInicio().toString(), request.getFechaFin().toString(), request.getVacunatorioId(), request.getPacienteDni());
			}
		}
		
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}
	
	private List<TurnoResponse> mapearTurnos(List<Object[]> listaDb) {
		List<TurnoResponse> listaResponse = new ArrayList<>();
		for(Object[] data : listaDb) {
			TurnoResponse turno = new TurnoResponse((Integer)data[0],(String)data[1],(String)data[2],(Integer)data[3],
					(String)data[4],(String)data[5],(String)data[6].toString(),(String)data[7].toString(),(Boolean)data[8]);
			listaResponse.add(turno);
		}
		return listaResponse;
	}

	public List<String> getTurnosAsignadosDespuesDeHoy() {
		String fechaHoy = LocalDate.now().toString();
		List<Object[]> listaDb = turnoRepository.getTurnosAsignadosDespuesDeHoy(fechaHoy);
		List<String> listaResonse = this.mapearStringTurnos(listaDb);
		return listaResonse;
	}
	
	public List<String> getTurnosHorasAsignadosEnFecha(String fecha) {
		List<Object[]> listaDb = turnoRepository.getTurnosHorasAsignadosEnFecha(fecha);
		List<String> listaResonse = this.mapearStringTurnos(listaDb);
		return listaResonse;
	}
	
	private List<String> mapearStringTurnos(List<Object[]> listaDb) {
		List<String> listaResponse = new ArrayList<>();
		for(Object[] fecha : listaDb) {
			listaResponse.add((String)fecha[0].toString());
		}
		return listaResponse;
	}

	public Boolean asignarTurnoFiebreAmarilla(CargarTurno turnoRequest) {
		
		if(turnoRequest.getAdminId().toString().isEmpty() || turnoRequest.getPacienteId().toString().isEmpty() 
				|| turnoRequest.getFechaTurno().isBlank() || turnoRequest.getHoraTurno().isBlank()) {
			return false;
		}
		else {
			Administrador administradorBuscado = administradorRepository.findById(turnoRequest.getAdminId()).get();
			Solicitud solicitudBuscada = solicitudRepository.findById(turnoRequest.getSolicitudId()).get();
			
			// Actualizo la solicitud
			solicitudBuscada.setAdministrador(administradorBuscado);
			solicitudBuscada.setAprobado(true);
			
			solicitudRepository.save(solicitudBuscada);
			
			Paciente pacienteBuscado = pacienteRepository.findById(turnoRequest.getPacienteId()).get();
			Vacunatorio vacunatorioPaciente = pacienteBuscado.getZona().getVacunatorio();
			Vacuna vacunaYellow = vacunaRepository.findById(5).get();
			
			LocalDate fecha = LocalDate.parse(turnoRequest.getFechaTurno(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalTime hora = LocalTime.parse(turnoRequest.getHoraTurno(), DateTimeFormatter.ofPattern("HH:mm"));
			LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
			
			Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), fechaHora, null, vacunaYellow,
					vacunatorioPaciente, pacienteBuscado);
			
			turnoRepository.save(nuevoTurno);
			
			return true;
		}
	}

	// METRICAS
	
	public List<MetricasResponse> getTurnosPendientesGripe() {
		List<Object[]> listaDb = turnoRepository.getTurnosPendientesGripe();
		List<MetricasResponse> listaResponse = this.mapearPendientes(listaDb);
		return listaResponse;
	}

	private List<MetricasResponse> mapearPendientes(List<Object[]> listaDb) {
		List<MetricasResponse> response = new ArrayList<>();
		for(Object[] obj : listaDb) {
			response.add(new MetricasResponse(((BigInteger)obj[0]).intValue(), ((BigInteger)obj[1]).intValue(), ((BigInteger)obj[2]).intValue(),
					((BigInteger)obj[3]).intValue()));
		}
		return response;
	}
	
	private List<ReporteResponse> mapearReporte(List<Object[]> listaDb) {
		List<ReporteResponse> response = new ArrayList<>();
		for(Object[] obj : listaDb) {
			response.add(new ReporteResponse(((BigInteger)obj[0]).intValue(), ((BigInteger)obj[1]).intValue(), ((BigInteger)obj[2]).intValue(),
					((BigInteger)obj[3]).intValue()));
		}
		return response;
	}
	
	public List<MetricasResponse> getTurnosPendientesTodasPorVacunatorio() {
		List<Object[]> listaDb = turnoRepository.getTurnosPendientesTodasPorVacunatorio();
		List<MetricasResponse> listaResponse = this.mapearPendientes(listaDb);
		return listaResponse;
	}

	public List<MetricasResponse> getTurnosPendientesTodas() {
		List<Object[]> listaDb = turnoRepository.getTurnosPendientesTodas();
		List<MetricasResponse> listaResponse = this.mapearPendientes(listaDb);
		return listaResponse;
	}

	public ReporteResponse getReporteCovid() {
		List<Object[]> listaDb = turnoRepository.getReporteCovid();
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	public ReporteResponse getReporteGripe() {
		List<Object[]> listaDb = turnoRepository.getReporteGripe();
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	public ReporteResponse getReporteYellow() {
		List<Object[]> listaDb = turnoRepository.getReporteYellow();
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	public List<TurnosPendientesPacienteResponse> getTurnosFuturosPorDni(Integer dni) {
		LocalDate fechaHoy = LocalDate.now();
		System.out.println(fechaHoy);
		List<Object[]> listaDb = turnoRepository.getTurnosFuturosPorDni(dni, fechaHoy.toString());
		List<TurnosPendientesPacienteResponse> listaResposne = this.maperTurnosPendientesPaciente(listaDb);
		return listaResposne;
	}

	private List<TurnosPendientesPacienteResponse> maperTurnosPendientesPaciente(List<Object[]> listaDb) {
		List<TurnosPendientesPacienteResponse> response = new ArrayList<>();
		for(Object[] obj : listaDb) {
			response.add(new TurnosPendientesPacienteResponse(
					(Integer)obj[0], (String)obj[1].toString(), (Integer)obj[2], (String)obj[3], (String)obj[4], (String)obj[5], (String)obj[6]));
		}
		return response;
	}

	public ReporteResponse getTurnosTotal() {
		List<Object[]> listaDb = turnoRepository.getTurnosTotal();
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	public List<ReporteTotalResponse> getTurnosTotalPorVacunatorio() {
		List<Object[]> listaDb = new ArrayList<>();
		for(int i = 1; i < 4; i++) {			
			listaDb.add(turnoRepository.getTurnosTotalPorVacunatorioConNombre(i).get(0));
		}
		List<ReporteTotalResponse> listaResponse = this.mapearReporteMetricas(listaDb);
		return listaResponse;
	}

	private List<ReporteTotalResponse> mapearReporteMetricas(List<Object[]> listaDb) {
		List<ReporteTotalResponse> response = new ArrayList<>();
		for(Object[] obj : listaDb) {
			response.add(new ReporteTotalResponse((String)obj[0], ((BigInteger)obj[1]).intValue(), ((BigInteger)obj[2]).intValue(), ((BigInteger)obj[3]).intValue(),
					((BigInteger)obj[4]).intValue()));
		}
		return response;
	}

	public ReporteResponse getTurnosTotalEnRango(String fechaInicio, String fechaFin) {
		System.out.println(fechaInicio);
		List<Object[]> listaDb = turnoRepository.getTurnosTotalEnRango(fechaInicio, fechaFin);
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	public List<ReporteTotalResponse> getTurnosTotalPorVacunatorioEnRango(String fechaInicio, String fechaFin) {
		List<Object[]> listaDb = new ArrayList<>();
		for(int i = 1; i < 4; i++) {			
			listaDb.add(turnoRepository.getTurnosTotalPorVacunatorioEnRangoConNombre(i, fechaInicio, fechaFin).get(0));
		}
		List<ReporteTotalResponse> listaResponse = this.mapearReporteMetricas(listaDb);
		return listaResponse;
	}

	public ReporteResponse getReporteCovidEnRango(String fechaInicio, String fechaFin) {
		List<Object[]> listaDb = turnoRepository.getReporteCovidEnRango(fechaInicio, fechaFin);
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	public ReporteResponse getReporteGripeEnRango(String fechaInicio, String fechaFin) {
		List<Object[]> listaDb = turnoRepository.getReporteGripeEnRango(fechaInicio, fechaFin);
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	public ReporteResponse getReporteYellowEnRango(String fechaInicio, String fechaFin) {
		List<Object[]> listaDb = turnoRepository.getReporteYellowEnRango(fechaInicio, fechaFin);
		List<ReporteResponse> listaResponse = this.mapearReporte(listaDb);
		return listaResponse.get(0);
	}

	// FIEBRE AMARILLA
	public Boolean eliminarTurnoFiebreAmarilla(Integer turnoId) {
		Turno turnoBuscado = turnoRepository.findById(turnoId).get();
		Solicitud solicitudBuscada = turnoBuscado.getPaciente().getSolicitud();
		System.out.println(turnoBuscado);
		System.out.println(solicitudBuscada);
		solicitudRepository.delete(solicitudBuscada);
		turnoRepository.delete(turnoBuscado);
		System.out.println(turnoBuscado);
		System.out.println(solicitudBuscada);
		return true;
	}
	
}
