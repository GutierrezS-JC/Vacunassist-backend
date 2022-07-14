package com.JDR.Vacunassist.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.PacienteDTO;
import com.JDR.Vacunassist.Dto.PacienteRequest;
import com.JDR.Vacunassist.Dto.PermisoDTO;
import com.JDR.Vacunassist.Dto.RolDTO;
import com.JDR.Vacunassist.Dto.SolicitudFiebreAmarilla;
import com.JDR.Vacunassist.Dto.SolicitudStatus;
import com.JDR.Vacunassist.Dto.TurnosPacienteResponse;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunasAnterioresRequest;
import com.JDR.Vacunassist.Dto.VacunasPacienteDetalle;
import com.JDR.Vacunassist.Dto.VacunasPacienteResponse;
import com.JDR.Vacunassist.Dto.VacunatorioDTO;
import com.JDR.Vacunassist.Dto.ValidarPaciente;
import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Model.Paciente;
import com.JDR.Vacunassist.Model.Permiso;
import com.JDR.Vacunassist.Model.Rol;
import com.JDR.Vacunassist.Model.Solicitud;
import com.JDR.Vacunassist.Model.Turno;
import com.JDR.Vacunassist.Model.Vacuna;
import com.JDR.Vacunassist.Model.Vacunador;
import com.JDR.Vacunassist.Model.Vacunatorio;
import com.JDR.Vacunassist.Model.Zona;
import com.JDR.Vacunassist.Repository.PacienteRepository;
import com.JDR.Vacunassist.Repository.RolRepository;
import com.JDR.Vacunassist.Repository.SolicitudRepository;
import com.JDR.Vacunassist.Repository.TurnoRepository;
import com.JDR.Vacunassist.Repository.VacunaRepository;
import com.JDR.Vacunassist.Repository.VacunatorioRepository;
import com.JDR.Vacunassist.Repository.ZonaRepository;

@Service
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	@Autowired
	RolRepository rolRepository;
	
	@Autowired
	ZonaRepository zonaRepository;
	
	@Autowired
	VacunatorioRepository vacunatorioRepository;
	
	@Autowired
	VacunaRepository vacunaRepository;
	
	@Autowired
	TurnoRepository turnoRepository;
	
	@Autowired
	SolicitudRepository solicitudRepository;
	
	public List<Integer> getDnisPacientes() {
		List<Paciente> pacientes = pacienteRepository.findAll();
		List<Integer> response = new ArrayList<>();
		if(pacientes != null) {
			for(Paciente paciente : pacientes) {
				response.add(paciente.getDni());
			}
			return response;
		}
		else {
			return null;
		}
	}

	public List<String> getEmailsPacientes() {
		List<Paciente> pacientes = pacienteRepository.findAll();
		List<String> response = new ArrayList<>();
		if(pacientes!=null) {
			for(Paciente paciente : pacientes) {
				response.add(paciente.getEmail());
			}
			return response;
		}
		else {
			return null;
		}
	}
	
	public String getPasswordPaciente(Integer dni) {
		Paciente pacienteBuscado = pacienteRepository.findByDni(dni);
		if(pacienteBuscado!= null) {
			return pacienteBuscado.getPassword();
		}
		return null;
	}
	
	public List<PacienteDTO> devolverPacientePorDni(Integer dni) {
		List<PacienteDTO> listaResponse = new ArrayList<>();
		Paciente paciente = pacienteRepository.findByDni(dni);
		if(paciente!= null) {
			PacienteDTO pacienteDto = mapearPaciente(paciente);
			listaResponse.add(pacienteDto);
			return listaResponse;
		}
		else return null;
	}
	
	public List<PacienteDTO> devolverPacientesEnZona(Integer zonaId) {
		List<Object[]> query = pacienteRepository.getPacientesEnZona(zonaId);
		List<PacienteDTO> response = new ArrayList<>();
		for(Object[] vacunDB : query) {
			Paciente pacienteBuscado = pacienteRepository.findByDni((Integer) vacunDB[1]);
			if(pacienteBuscado!= null) {
				response.add(this.mapearPaciente(pacienteBuscado));
			}
		}
		return (response.size() == 0 ?  null :  response);
	}
	
	public List<PacienteDTO> getPacientes() {
		List<Paciente> pacienteList = pacienteRepository.findAll();
		List<PacienteDTO> response = this.convertirPaciente(pacienteList);
		return response;	
	}

	private List<PacienteDTO> convertirPaciente(List<Paciente> pacienteList) {
		return pacienteList.stream().map(paciente -> mapearPaciente(paciente)).collect(Collectors.toList());
	}
	
	private PacienteDTO mapearPaciente(Paciente paciente) {
		List<PermisoDTO> listaPermisosDTO = new ArrayList<>();
		for(Permiso permiso : paciente.getRol().getPermisos()) {
			PermisoDTO permisoNuevo = new PermisoDTO(permiso.getId(), permiso.getNombrePermiso());
			listaPermisosDTO.add(permisoNuevo);
		}
		
		VacunatorioDTO vacunatorioDTO = new VacunatorioDTO(paciente.getZona().getVacunatorio().getId(), paciente.getZona().getVacunatorio().getNombre());
		ZonaDTO zonaNueva = new ZonaDTO(paciente.getZona().getId(), paciente.getZona().getNombreZona(), vacunatorioDTO);
		
		PacienteDTO pacienteDTO = new PacienteDTO(paciente.getId(), paciente.getDni(), paciente.getEmail(), paciente.getNombre(), paciente.getApellido(),
				paciente.getFechaNacimiento(), paciente.getEsDeRiesgo(), new RolDTO(paciente.getRol().getId(), paciente.getRol().getNombreRol(), listaPermisosDTO),
				zonaNueva);
		
		return pacienteDTO;
	}
	
	public Boolean validarPacienteBoolean(ValidarPaciente validarPaciente) {
		Paciente pacienteBuscado = pacienteRepository.findByEmailAndPassword(validarPaciente.getEmail(), validarPaciente.getPassword());
		if(pacienteBuscado != null) {
			return true;
		}
		else {			
			return false;
		}
	}
	
	public PacienteDTO validarPacienteConCodigo(ValidarPaciente validarPaciente) {
		Paciente pacienteBuscado = pacienteRepository.findByEmailAndPasswordAndCodigo(validarPaciente.getEmail(), validarPaciente.getPassword(), validarPaciente.getCodigo());
		if(pacienteBuscado != null) {
			PacienteDTO pacienteDTO = this.mapearPaciente(pacienteBuscado);
			return pacienteDTO;
		}
		else {			
			return null;
		}
	}
	
	public Boolean solicitarTurnoFiebreAmarilla(SolicitudFiebreAmarilla solicitudRequest) {
		Object objectPaciente = pacienteRepository.getAlreadyHasYellow(5, solicitudRequest.getPacienteId());
		
		//No tiene turno registrado de fiebre amarilla --> Creamos solicitud
		if(objectPaciente == null) {
			Paciente pacienteBuscado = pacienteRepository.findByDni(solicitudRequest.getDni());
			if(convertirALocalDate(new Date()).getYear() - pacienteBuscado.getFechaNacimiento().getYear() < 60) {
				Solicitud solicitudNueva = new Solicitud(0,LocalDate.now(), LocalDate.now(), null, null, pacienteBuscado);
				solicitudRepository.saveAndFlush(solicitudNueva);
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public List<TurnosPacienteResponse> getTurnosPaciente(Integer pacienteId) {
		List<Object[]> listaDB = pacienteRepository.getTurnosPaciente(pacienteId);
		List<TurnosPacienteResponse> listaResponse = new ArrayList<>();
		if(listaDB != null) {
			for(Object[] objectDB : listaDB){
				
				TurnosPacienteResponse nuevaResponse= new TurnosPacienteResponse((Integer)objectDB[0],(Integer)objectDB[1],
						(String)objectDB[2].toString(),
						(String)objectDB[3].toString(), (Boolean)objectDB[4], (String)objectDB[5], (String)objectDB[6],
						(String)objectDB[7], (String)objectDB[8], (String)objectDB[9], (Integer)objectDB[10]);
				listaResponse.add(nuevaResponse);
			}
		}
		return listaResponse; 
	}
	
	public List<VacunasPacienteResponse> getVacunasPaciente(Integer pacienteId) {
		Paciente pacienteBuscado = pacienteRepository.findById(pacienteId).orElse(null);
		if (pacienteBuscado != null) {			
			List<VacunasPacienteResponse> listaResponse = new ArrayList<>();
			VacunasPacienteResponse vacunasCovidPaciente = this.getVacunasCovidPaciente(pacienteId);
			VacunasPacienteResponse vacunasGripePaciente = this.getVacunasGripePaciente(pacienteId);
			VacunasPacienteResponse vacunasFiebreAmarillaPaciente = this.getVacunasFiebreAmarillaPaciente(pacienteId);
			if(vacunasCovidPaciente == null) {
				vacunasCovidPaciente = this.falsificarCovid();
			}
			if(vacunasGripePaciente == null) {
				vacunasGripePaciente = this.falsificarGripe();
			}
			if(vacunasFiebreAmarillaPaciente == null) {
				vacunasFiebreAmarillaPaciente = this.falsificarFiebreAmarilla();
			}
			listaResponse.add(vacunasCovidPaciente);
			listaResponse.add(vacunasGripePaciente);
			listaResponse.add(vacunasFiebreAmarillaPaciente);
			return listaResponse;
		}
		return null;
	}
	
	private VacunasPacienteResponse falsificarFiebreAmarilla() {
		return new VacunasPacienteResponse(5,"Fiebre Amarilla", "-");
	}

	private VacunasPacienteResponse falsificarGripe() {
		return new VacunasPacienteResponse(4,"Gripe", "-");
	}

	private VacunasPacienteResponse falsificarCovid() {
		return new VacunasPacienteResponse(1,"Covid", "-");
	}

	public VacunasPacienteResponse getVacunasCovidPaciente(Integer pacienteId) {
		List<Object[]> listaDB = pacienteRepository.getVacunasCovidPaciente(pacienteId);
		List<VacunasPacienteDetalle> listaDetalles = new ArrayList<>();
		VacunasPacienteResponse vacunaPacienteResponse = null;
		boolean set = false;
		if(listaDB.size() != 0) {
			for(Object[] objectDB : listaDB) {
				if(set == false) {
					vacunaPacienteResponse = new VacunasPacienteResponse((Integer)objectDB[0],(String)objectDB[1], (String)objectDB[2].toString());
					set = true;
				}
				VacunasPacienteDetalle detalle = new VacunasPacienteDetalle((String)objectDB[3].toString(), (Integer)objectDB[4], (String)objectDB[5],
                        (Integer)objectDB[6], (String)objectDB[7], (Integer)objectDB[8], (String)objectDB[9]);
				listaDetalles.add(detalle);
			}
			vacunaPacienteResponse.setListaDetalles(listaDetalles);
		}
		return vacunaPacienteResponse;
	}
	
	public VacunasPacienteResponse getVacunasFiebreAmarillaPaciente(Integer pacienteId) {
		List<Object[]> listaDB = pacienteRepository.getVacunasFiebreAmarillaPaciente(pacienteId);
		List<VacunasPacienteDetalle> listaDetalles = new ArrayList<>();
		VacunasPacienteResponse vacunaPacienteResponse = null;
		boolean set = false;
		if(listaDB.size() != 0) {
			for(Object[] objectDB : listaDB) {
				if(set == false) {
					vacunaPacienteResponse = new VacunasPacienteResponse((Integer)objectDB[0],(String)objectDB[1], (String)objectDB[2].toString());
					set = true;
				}
				VacunasPacienteDetalle detalle = new VacunasPacienteDetalle((String)objectDB[3].toString(), (Integer)objectDB[4], (String)objectDB[5],
                        (Integer)objectDB[6], (String)objectDB[7], (Integer)objectDB[8], (String)objectDB[9]);
				listaDetalles.add(detalle);
			}
			vacunaPacienteResponse.setListaDetalles(listaDetalles);
		}
		return vacunaPacienteResponse;
	}
	
	public VacunasPacienteResponse getVacunasGripePaciente(Integer pacienteId) {
		List<Object[]> listaDB = pacienteRepository.getVacunasGripePaciente(pacienteId);
		List<VacunasPacienteDetalle> listaDetalles = new ArrayList<>();
		VacunasPacienteResponse vacunaPacienteResponse = null;
		boolean set = false;
		if(listaDB.size() != 0) {
			for(Object[] objectDB : listaDB) {
				if(set == false) {
					vacunaPacienteResponse = new VacunasPacienteResponse((Integer)objectDB[0],(String)objectDB[1], (String)objectDB[2].toString());
					set = true;
				}
				VacunasPacienteDetalle detalle = new VacunasPacienteDetalle((String)objectDB[3].toString(), (Integer)objectDB[4], (String)objectDB[5],
                        (Integer)objectDB[6], (String)objectDB[7], (Integer)objectDB[8], (String)objectDB[9]);
				listaDetalles.add(detalle);
			}
			vacunaPacienteResponse.setListaDetalles(listaDetalles);
		}
		return vacunaPacienteResponse;
	}
	
	public Boolean getTieneSolicitudFiebreAmarillaPaciente(Integer pacienteId) {
		Paciente pacienteBuscado = pacienteRepository.findById(pacienteId).orElse(null);
		if(pacienteBuscado != null) {
			return pacienteBuscado.getSolicitud() == null ? false : true;
		}
		return false;
	}
	
	public SolicitudStatus getTieneSolicitudFiebreAmarillaPacienteV2(Integer pacienteId) {
		Paciente pacienteBuscado = pacienteRepository.findById(pacienteId).orElse(null);
		SolicitudStatus solicitudStatus = new SolicitudStatus();
		if(pacienteBuscado.getSolicitud() != null ) {
			solicitudStatus.setTieneSolicitud(true);
			if(pacienteBuscado.getSolicitud().getAprobado() != null) {
				solicitudStatus.setAceptada(pacienteBuscado.getSolicitud().getAprobado());
			}
		}
		return solicitudStatus;
	}

	// ====================== REGISTRO ====================== //
	
	private LocalDate convertirALocalDate(Date dateToConvert) {
	    return LocalDate.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
	
    private String compareDatesStr(String date1, String date2) {
        if (date1 == null)
            if (date2 == null)
                return "not seen yet";
            else
                return date2;
        else if (date2 == null)
            return date1;
        else
            return date1.compareTo(date2) > 0 ? date1 : date2;
    }
	
	public Integer cargarPaciente(PacienteRequest pacienteRequest) {
		
		Paciente pacienteExiste = pacienteRepository.findByDni(pacienteRequest.getDni());
		if(pacienteExiste == null) {
			if(!pacienteRequest.getNombre().trim().isBlank() && !pacienteRequest.getApellido().trim().isBlank() && !pacienteRequest.getEmail().isBlank() 
					&& !pacienteRequest.getPassword().isBlank() && !pacienteRequest.getZonaId().toString().isBlank() 
					&& !pacienteRequest.getZonaId().toString().isBlank()) {
				
				Rol rolPaciente = rolRepository.findById(3).get();
				Zona zonaPaciente = zonaRepository.findById(pacienteRequest.getZonaId()).get();
				
				boolean seVacunoPreviamenteCovid = false;
				boolean seVacunoPreviamenteGripe = false;
				LocalDate fechaVacunaGripeAnterior = null;
				LocalDate fechaVacunaCovidAnterior = null;
				String fechaMaxCovid =  "1900-01-01";
				
				Integer codigoUnico = pacienteRepository.getUltimoCodigoCreado();
				codigoUnico = codigoUnico + 1;
				
				// Si es un viejisto aunque no haya seleccionado la opcion de que es de riesgo aca lo seteamos como TRUE //
				if(convertirALocalDate(new Date()).getYear() - pacienteRequest.getFechaNacimiento().getYear() >= 60 ){
					pacienteRequest.setEsRiesgo(true);
				}
				
				Paciente paciente = new Paciente(pacienteRequest.getDni(), pacienteRequest.getEmail(), pacienteRequest.getPassword(), codigoUnico,
						pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getFechaNacimiento(), pacienteRequest.getEsRiesgo(), rolPaciente, zonaPaciente);
				
				Paciente nuevoPaciente = pacienteRepository.saveAndFlush(paciente);
	
				if(!pacienteRequest.getListaVacunasAnteriores().isEmpty()) {
					for(VacunasAnterioresRequest vacunaAnterior : pacienteRequest.getListaVacunasAnteriores()) {		

						Vacuna vacunaAnteriorBuscada= vacunaRepository.findById(vacunaAnterior.getVacunaId()).get();
						Vacunatorio vacunatorioAnterior = vacunatorioRepository.getVacunatorioByZona(vacunaAnterior.getZonaId());
						
						//Vemos si dentro de las vacunas previas tiene una correspondiente al COVID
						if(vacunaAnteriorBuscada.getId() == 1 || vacunaAnteriorBuscada.getId() == 2 || vacunaAnteriorBuscada.getId() == 3){
							seVacunoPreviamenteCovid = true;
							fechaMaxCovid = compareDatesStr(fechaMaxCovid, vacunaAnterior.getFechaAplicacion().toLocalDate().toString());
						}
						
						//Vemos si dentro de las vacunas previas tiene una correspondiente a la Gripe
						if(vacunaAnteriorBuscada.getId() == 4) {
							seVacunoPreviamenteGripe = true;
							fechaVacunaGripeAnterior = vacunaAnterior.getFechaAplicacion().toLocalDate();
						}
						
						System.out.println("Fecha covid setMax: " + fechaMaxCovid);
						fechaVacunaCovidAnterior = LocalDate.parse(fechaMaxCovid);
						
						Turno turnoPasado = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), vacunaAnterior.getFechaAplicacion(), true, vacunaAnteriorBuscada ,vacunatorioAnterior, nuevoPaciente);
						turnoRepository.save(turnoPasado);
					}
					System.out.println("Fecha covid max definitiva: " + fechaMaxCovid);
				}
				
				// == EMPIEZO CONDICIONES PARA TURNOS == //
				
				// SI ES MAYOR A 60 //
				if(convertirALocalDate(new Date()).getYear() - pacienteRequest.getFechaNacimiento().getYear() >= 60 ){
					LocalDateTime fecha = LocalDateTime.of(LocalDate.now(), LocalTime.now());
				
					Vacunatorio vacunatorioElegido = vacunatorioRepository.getVacunatorioByZona(pacienteRequest.getZonaId());
					
					if(!seVacunoPreviamenteGripe || (seVacunoPreviamenteGripe && ChronoUnit.MONTHS.between(fechaVacunaGripeAnterior, convertirALocalDate(new Date())) >= 12)) {
						fecha = fecha.plusMonths(3);
						
						Vacuna vacunaGripe = vacunaRepository.findByNombre("Gripe");
						
//						String fechaQuery = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						
						LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha.toLocalDate(), vacunatorioElegido.getId());
//						Object ultimoTurnoFecha = pacienteRepository.getUltimoTurnoEnFecha(fechaQuery, vacunatorioElegido.getId());
						Turno nuevoTurno;
						
						nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, 
							null, vacunaGripe,vacunatorioElegido, nuevoPaciente);
						
						turnoRepository.saveAndFlush(nuevoTurno);
					}
										
					if(!seVacunoPreviamenteCovid || (seVacunoPreviamenteCovid && ChronoUnit.MONTHS.between(fechaVacunaCovidAnterior, convertirALocalDate(new Date())) >= 3)) {
						fecha = LocalDateTime.of(LocalDate.now(), LocalTime.now());

						fecha = fecha.plusDays(7);
						System.out.println("(COVID) Fecha plus 7 dias - 193: " + fecha);
						Vacuna vacunaCovid = vacunaRepository.getVacunaCovidRandom();

						String fechaQuery = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						System.out.println("(COVID) Fecha Query Covid - 197: " + fechaQuery);
						
//						Object ultimoTurnoFecha = pacienteRepository.getUltimoTurnoEnFecha(fechaQuery, vacunatorioElegido.getId());
						LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha.toLocalDate(), vacunatorioElegido.getId());
						Turno nuevoTurno;
						
						nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, 
								null, vacunaCovid,vacunatorioElegido, nuevoPaciente);
							
						turnoRepository.saveAndFlush(nuevoTurno);
					}
//					pacienteRepository.save(nuevoPaciente);
				}
				
				// SI ES MENOR A 18 //
				if(convertirALocalDate(new Date()).getYear() - pacienteRequest.getFechaNacimiento().getYear() < 18 ){
					LocalDateTime fecha = LocalDateTime.of(LocalDate.now(), LocalTime.now());
					Vacunatorio vacunatorioElegido = vacunatorioRepository.getVacunatorioByZona(pacienteRequest.getZonaId());					
					
					if(pacienteRequest.getEsRiesgo()) {
						if(!seVacunoPreviamenteGripe || (seVacunoPreviamenteGripe && ChronoUnit.MONTHS.between(fechaVacunaGripeAnterior, convertirALocalDate(new Date())) >= 12)) {
			
							String fechaQuery = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							System.out.println("Fecha Query: " + fechaQuery);
//							Object ultimoTurnoFecha = pacienteRepository.getUltimoTurnoEnFecha(fechaQuery, vacunatorioElegido.getId());
							fecha = fecha.plusMonths(3);
						
							LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha.toLocalDate(), vacunatorioElegido.getId());
							Vacuna vacunaGripe = vacunaRepository.findByNombre("Gripe");
							Turno nuevoTurno;
								
							nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, 
									null, vacunaGripe,vacunatorioElegido, nuevoPaciente);
							turnoRepository.saveAndFlush(nuevoTurno);
						}
					}
					else {
						if(!seVacunoPreviamenteGripe || (seVacunoPreviamenteGripe && ChronoUnit.MONTHS.between(fechaVacunaGripeAnterior, convertirALocalDate(new Date())) >= 12)) {
							fecha = fecha.plusMonths(6);
							String fechaQuery = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							System.out.println("Fecha Query: " + fechaQuery);
//							Object ultimoTurnoFecha = pacienteRepository.getUltimoTurnoEnFecha(fechaQuery, vacunatorioElegido.getId());
							Vacuna vacunaGripe = vacunaRepository.findByNombre("Gripe");
							Turno nuevoTurno;
							
							LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha.toLocalDate(), vacunatorioElegido.getId());
							
							nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, 
									null, vacunaGripe,vacunatorioElegido, nuevoPaciente);
							turnoRepository.saveAndFlush(nuevoTurno);
						}
					}
					pacienteRepository.save(nuevoPaciente);
				}
								
				// SI ES MAYOR O TIENE 18 Y ES MENOR QUE 60 //
				if(convertirALocalDate(new Date()).getYear() - pacienteRequest.getFechaNacimiento().getYear() >= 18 &&
					convertirALocalDate(new Date()).getYear() - pacienteRequest.getFechaNacimiento().getYear()<= 59){
					
					LocalDateTime fecha = LocalDateTime.of(LocalDate.now(), LocalTime.now());
					Vacunatorio vacunatorioElegido = vacunatorioRepository.getVacunatorioByZona(pacienteRequest.getZonaId());
					
					if(pacienteRequest.getEsRiesgo()) {
						if(!seVacunoPreviamenteGripe || (seVacunoPreviamenteGripe && ChronoUnit.MONTHS.between(fechaVacunaGripeAnterior, convertirALocalDate(new Date())) >= 12)) {
							fecha = fecha.plusMonths(3);
							Vacuna vacunaGripe = vacunaRepository.findByNombre("Gripe");
							
//							Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), fecha, null, vacunaGripe, vacunatorioElegido, nuevoPaciente);
							
							String fechaQuery = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							System.out.println("Fecha Query: " + fechaQuery);
//							Object ultimoTurnoFecha = pacienteRepository.getUltimoTurnoEnFecha(fechaQuery, vacunatorioElegido.getId());
							Turno nuevoTurno;
							
							LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha.toLocalDate(), vacunatorioElegido.getId());
							nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, 
									null, vacunaGripe,vacunatorioElegido, nuevoPaciente);
							
							turnoRepository.saveAndFlush(nuevoTurno);
						}
						
						if(!seVacunoPreviamenteCovid || (seVacunoPreviamenteCovid && ChronoUnit.MONTHS.between(fechaVacunaCovidAnterior, convertirALocalDate(new Date())) >= 3)) {
							fecha = LocalDateTime.of(LocalDate.now(), LocalTime.now());
							
							fecha = fecha.plusDays(7);
							Vacuna vacunaCovid = vacunaRepository.getVacunaCovidRandom();
							
//							Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), fecha, null, vacunaCovid, vacunatorioElegido, nuevoPaciente);
							
							String fechaQuery = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							System.out.println("Fecha Query: " + fechaQuery);
//							Object ultimoTurnoFecha = pacienteRepository.getUltimoTurnoEnFecha(fechaQuery, vacunatorioElegido.getId());
							Turno nuevoTurno;
							
							LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha.toLocalDate(), vacunatorioElegido.getId());
							nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, 
									null, vacunaCovid,vacunatorioElegido, nuevoPaciente);
							turnoRepository.saveAndFlush(nuevoTurno);
						}
						
						
					}
					else {
						if(!seVacunoPreviamenteGripe || (seVacunoPreviamenteGripe && ChronoUnit.MONTHS.between(fechaVacunaGripeAnterior, convertirALocalDate(new Date())) >= 12)) {
							fecha = fecha.plusMonths(6);
							Vacuna vacunaGripe = vacunaRepository.findByNombre("Gripe");
//							Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), fecha, null, vacunaGripe, vacunatorioElegido, nuevoPaciente);
							String fechaQuery = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							System.out.println("Fecha Query: " + fechaQuery);
//							Object ultimoTurnoFecha = pacienteRepository.getUltimoTurnoEnFecha(fechaQuery, vacunatorioElegido.getId());
							Turno nuevoTurno;
							
							LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha.toLocalDate(), vacunatorioElegido.getId());
							nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, 
									null, vacunaGripe,vacunatorioElegido, nuevoPaciente);
							turnoRepository.saveAndFlush(nuevoTurno);
						}
					}
					pacienteRepository.save(nuevoPaciente);
				}
				//Devuelvo el codigo despues de haber asignado los turnos correspondientes
				return nuevoPaciente.getCodigo();
			} 
			//Existe un input vacio
			else {
				return null;
			}
		}
		//Paciente ya existe en el sistema
		else {
			return null;
		}
	}

	public Boolean deletePaciente(Integer pacienteId) {
		Paciente pacienteBuscado = pacienteRepository.findById(pacienteId).orElse(null);
		if(pacienteBuscado != null) {
			pacienteRepository.deleteById(pacienteId);
			return true;
		}
		return false;
	}
	
	public PacienteDTO editarPacienteObject(String nombre, String apellido, String password, Integer idZona,
			Integer dni, Boolean deRiesgo) {
		Zona zona = zonaRepository.findById(idZona).get();
        Paciente pacienteBuscado = pacienteRepository.findByDni(dni);
        if(pacienteBuscado != null) {
            if(!pacienteBuscado.getNombre().trim().equals(nombre) && !nombre.isBlank()) {    //si nombre no es = a el nombre original y si no es blank, cambia el nombre
            	pacienteBuscado.setNombre(nombre);
            }
            if(!pacienteBuscado.getApellido().trim().equals(apellido) && !apellido.isBlank()) {    //si apellido no es = a el apellido original y si no es blank, cambia el apellido
            	pacienteBuscado.setApellido(apellido);
            }
            if(!pacienteBuscado.getPassword().equals(password) && !password.isBlank()) {    //si password no es = a la password original y si no es blank, cambia la passsword
                pacienteBuscado.setPassword(password);
            }
            
            if(!pacienteBuscado.getZona().getNombreZona().equals(zona.getNombreZona())) {    // si zona no es = a la asignada actualmente, lo cambia de zona
            	pacienteBuscado.setZona(zona);
            }
            
            if(!pacienteBuscado.getEsDeRiesgo().equals(deRiesgo)) {
            	pacienteBuscado.setEsDeRiesgo(deRiesgo);
            }
          
            PacienteDTO pacienteResponse = this.mapearPaciente(pacienteBuscado);
            pacienteRepository.save(pacienteBuscado); //actualiza los datos
            return pacienteResponse;
        } else {
            return null;
        }
	}

	public Boolean reasignarTurno(Integer turnoId) {
		Turno turnoBuscado = turnoRepository.findById(turnoId).get();
		
//		System.out.println(turnoBuscado.getFechaAplicacion().toLocalDate());
//		LocalDate fecha = turnoBuscado.getFechaAplicacion().toLocalDate();
//		LocalTime hora = LocalTime.parse(turnoBuscado.getFechaAplicacion().toLocalTime().toString(), DateTimeFormatter.ofPattern("HH:mm"));
//		System.out.println(hora);
//		System.out.println(turnoBuscado.getFechaAplicacion().toLocalTime().toString());
//		System.out.println(fecha.plusDays(230));
//		LocalTime horaInicio = LocalTime.parse(("10:00"), DateTimeFormatter.ofPattern("HH:mm"));
//		System.out.println(horaInicio);
//		
//		Object[] turnoBd = pacienteRepository.buscarTurnoExiste("2022-12-23","10:30"); 
//		System.out.println(turnoBd.length);
//		System.out.println(turnoBd.length == 0);
//		
//		LocalDate fechaPrueba = LocalDate.parse(("2022-12-25"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		System.out.println("Fecha Prueba: " + fechaPrueba);
//		LocalDateTime prueba = this.buscarTurnoLibre(fechaPrueba);
//		System.out.println("El dia encontrado es: " + prueba);
		
		Paciente pacienteAsignado = turnoBuscado.getPaciente();
		Vacuna vacuna = turnoBuscado.getVacuna();
		Vacunatorio vacunatorio = turnoBuscado.getVacunatorio();
		
		if(!pacienteAsignado.getEsDeRiesgo()) {
			// Covid sin riesgo --> Solo elimino turno
			if(turnoBuscado.getVacuna().getId() == 1 || turnoBuscado.getVacuna().getId() == 2 || turnoBuscado.getVacuna().getId() == 3) {
				turnoRepository.delete(turnoBuscado);
			}
			// Gripe sin riesgo --> 1 MES
			else if (turnoBuscado.getVacuna().getId() == 4) {
				LocalDate fecha = turnoBuscado.getFechaAplicacion().toLocalDate();
				fecha = fecha.plusMonths(1);
				LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha, vacunatorio.getId());
				turnoRepository.delete(turnoBuscado);
				
				// Asignar nuevo turno
				Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, null, 
						vacuna, vacunatorio, pacienteAsignado);
				turnoRepository.saveAndFlush(nuevoTurno);
			}
		}
		else {
			// Covid o gripe con riesgo --> 1 SEMANA
			if(turnoBuscado.getVacuna().getId() == 1 || turnoBuscado.getVacuna().getId() == 2 ||
					turnoBuscado.getVacuna().getId() == 3 || turnoBuscado.getVacuna().getId() == 4) {
				LocalDate fecha = turnoBuscado.getFechaAplicacion().toLocalDate();
				fecha = fecha.plusWeeks(1);
				LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha, vacunatorio.getId());
				turnoRepository.delete(turnoBuscado);
				
				// Asignar nuevo turno
				Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, null, 
						vacuna, vacunatorio, pacienteAsignado);
				turnoRepository.saveAndFlush(nuevoTurno);
			}
		}
		return true;
	}

	// Busca el primer turno libre a partir de la fecha ingresada y vacunatorio;
	// Ignora los dias Sabado y Domingo 
	private LocalDateTime buscarTurnoLibre(LocalDate fecha, Integer vacunatorioId) {
		LocalDate fechaLibre = null;
		LocalTime horaLibre = null;
		boolean encontre = false;
		LocalTime finalJornada = LocalTime.parse(("17:00"), DateTimeFormatter.ofPattern("HH:mm"));
		
		while(!encontre) {
			
			if(fecha.getDayOfWeek() != DayOfWeek.SATURDAY && fecha.getDayOfWeek() != DayOfWeek.SUNDAY) {			
				LocalTime horaInicio = LocalTime.parse(("10:00"), DateTimeFormatter.ofPattern("HH:mm"));
				
				//Loop en el mismo dia pero con horas intervalo de 15 minutos
				while(horaInicio.isBefore(finalJornada)) {
					Object[] turnoBd = pacienteRepository.buscarTurnoExisteEnVacun(fecha.toString(),horaInicio.toString(), vacunatorioId); 
					
					// Si el turno buscado en ese dia y hora es NULL && no encontre una fecha antes (bandera) --> LO ENCONTRE!
					if(turnoBd.length == 0 && !encontre) {
						encontre = true;
						fechaLibre = fecha;
						horaLibre = horaInicio;
						System.out.println("Encontre: " + fechaLibre + " " + horaLibre);
					}
					horaInicio = horaInicio.plusMinutes(15);
				}
			}
			// Es un finde || Termine la jornada --> Sumo un dia para seguir loopeando
			fecha = fecha.plusDays(1);
		}
		return LocalDateTime.of(fechaLibre, horaLibre);
	}
	
}
