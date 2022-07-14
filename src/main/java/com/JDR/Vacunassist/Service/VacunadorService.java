package com.JDR.Vacunassist.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.PermisoDTO;
import com.JDR.Vacunassist.Dto.RolDTO;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunadorRequest;
import com.JDR.Vacunassist.Dto.VacunatorioDTO;
import com.JDR.Vacunassist.Dto.ValidarVacunador;
import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Model.Paciente;
import com.JDR.Vacunassist.Model.Permiso;
import com.JDR.Vacunassist.Model.Rol;
import com.JDR.Vacunassist.Model.Solicitud;
import com.JDR.Vacunassist.Model.Turno;
import com.JDR.Vacunassist.Model.Vacuna;
import com.JDR.Vacunassist.Model.Vacunador;
import com.JDR.Vacunassist.Model.Vacunatorio;
import com.JDR.Vacunassist.Model.VacunatorioVacuna;
import com.JDR.Vacunassist.Model.Zona;
import com.JDR.Vacunassist.Repository.PacienteRepository;
import com.JDR.Vacunassist.Repository.RolRepository;
import com.JDR.Vacunassist.Repository.SolicitudRepository;
import com.JDR.Vacunassist.Repository.TurnoRepository;
import com.JDR.Vacunassist.Repository.VacunadorRepository;
import com.JDR.Vacunassist.Repository.VacunatorioVacunaRepository;
import com.JDR.Vacunassist.Repository.ZonaRepository;

@Service
public class VacunadorService {

	@Autowired
	VacunadorRepository vacunadorRepository;
	
	@Autowired
	RolRepository rolRepository;
	
	@Autowired
	ZonaRepository zonaRepository;
	
	@Autowired
	TurnoRepository turnoRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	SolicitudRepository solicitudRepository;
	
	@Autowired
	VacunatorioVacunaRepository vacunatorioVacunaRepository;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	public List<VacunadorDTO> devolverVacunadores() {
		List<Vacunador> vacunadorList = vacunadorRepository.findAll();
		List<VacunadorDTO> response = this.convertirVacunador(vacunadorList);
		return response;	
	}

	//Convierte un objeto Vacunador traido con toda la data redundante de la BD a una version cutom por nosotros
	private List<VacunadorDTO> convertirVacunador(List<Vacunador> vacunadorList) {
		return vacunadorList.stream().map(vacunador -> mapearVacunador(vacunador)).collect(Collectors.toList());
	}

	private VacunadorDTO mapearVacunador(Vacunador vacunador) {
		VacunadorDTO vacunadorDTO = new VacunadorDTO();
		List<PermisoDTO> listaPermisosDTO = new ArrayList<>();
//		List<ZonaDTO> listaZonasDTO = new ArrayList<>();
		
		for(Permiso permiso : vacunador.getRol().getPermisos()) {
			PermisoDTO permisoNuevo = new PermisoDTO(permiso.getId(), permiso.getNombrePermiso());
			listaPermisosDTO.add(permisoNuevo);
		}
		
//		for(VacunadorZona vacunadorZona : vacunador.getZonas()) {
//			VacunatorioDTO vacunatorioDTO = new VacunatorioDTO(vacunadorZona.getZona().getVacunatorio().getId(), vacunadorZona.getZona().getVacunatorio().getNombre());
//			ZonaDTO zonaNueva = new ZonaDTO(vacunadorZona.getZona().getId(), vacunadorZona.getZona().getNombreZona(), vacunatorioDTO);
//			listaZonasDTO.add(zonaNueva);
//		}
		
		VacunatorioDTO vacunatorioDTO = new VacunatorioDTO(vacunador.getZona().getVacunatorio().getId(), vacunador.getZona().getVacunatorio().getNombre());
		ZonaDTO zonaNueva = new ZonaDTO(vacunador.getZona().getId(), vacunador.getZona().getNombreZona(), vacunatorioDTO);
		
		vacunadorDTO.setId(vacunador.getId());
		vacunadorDTO.setDni(vacunador.getDni());
		vacunadorDTO.setEmail(vacunador.getEmail());
		vacunadorDTO.setNombre(vacunador.getNombre());
		vacunadorDTO.setApellido(vacunador.getApellido());
		vacunadorDTO.setFechaNacimiento(vacunador.getFechaNacimiento());
		vacunadorDTO.setRol(new RolDTO(vacunador.getRol().getId(), vacunador.getRol().getNombreRol(), listaPermisosDTO));
		vacunadorDTO.setZona(zonaNueva);
		
		return vacunadorDTO;
	}

	public VacunadorDTO devolverVacunadorPorId(Integer id) throws ResourceNotFoundException{
		Vacunador vacunador = vacunadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro un admin para este id: " + id));
		return mapearVacunador(vacunador);
	}

	public List<VacunadorDTO> devolverVacunadorPorDni(Integer dni) {
		List<VacunadorDTO> listaResponse = new ArrayList<>();
		Vacunador vacunador = vacunadorRepository.findByDni(dni);
		if(vacunador!= null) {
			VacunadorDTO vacunadorDto = mapearVacunador(vacunador);
			listaResponse.add(vacunadorDto);
			return listaResponse;
		}
		else return null;
	}

	public List<VacunadorDTO> devolverVacunadoresEnRango(Integer inferiorDni, Integer superiorDni) {
		List<Vacunador> vacunadorList = vacunadorRepository.findByDniGreaterThanAndDniLessThan(inferiorDni, superiorDni);
		List<VacunadorDTO> response = this.convertirVacunador(vacunadorList);
		return response;
	}

	public Boolean devolverSiExisteDniEnVacunadorTable(Integer dni) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByDni(dni);
		if(vacunadorBuscado !=null) return true; else return false;
	}

	public VacunadorDTO cargarVacunador(VacunadorRequest vacunadorReq) {
		Rol rolBuscado = rolRepository.findById(vacunadorReq.getRolId()).get();
		Zona zona = zonaRepository.findById(vacunadorReq.getZonaId()).get();
		
		Vacunador nuevoVacunador = new Vacunador(vacunadorReq.getId(), vacunadorReq.getDni(), vacunadorReq.getEmail(), vacunadorReq.getPassword(),
				vacunadorReq.getClave(), vacunadorReq.getNombre(), vacunadorReq.getApellido(), vacunadorReq.getFechaNacimiento(), rolBuscado, zona);
	
		Vacunador vacunadorCreado = vacunadorRepository.saveAndFlush(nuevoVacunador);

		//Creo el objeto VacunadorZona que representa la union entre ambas entidades participantes
//		VacunadorZona vacunadorZona = new VacunadorZona(vacunadorCreado, zona);
//		vacunadorCreado.getZonas().add(vacunadorZona);
//		vacunadorRepository.saveAndFlush(vacunadorCreado);
		VacunadorDTO vacunadorResponse = this.mapearVacunador(vacunadorCreado);
		
		return vacunadorResponse;
	}

	public VacunadorDTO validarVacunador(ValidarVacunador validarVacunador) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByEmailAndPassword(validarVacunador.getEmail(), validarVacunador.getPassword());
		if(vacunadorBuscado != null) {
			VacunadorDTO vacunadorDTO = this.mapearVacunador(vacunadorBuscado);
			return vacunadorDTO;
		}
		else {			
			return null;
		}
	}

	public Boolean validarVacunadorBoolean(ValidarVacunador validarVacunador) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByEmailAndPassword(validarVacunador.getEmail(), validarVacunador.getPassword());
		if(vacunadorBuscado != null) {
			return true;
		}
		else {			
			return false;
		}
	}

	public VacunadorDTO validarVacunadorConCodigo(ValidarVacunador validarVacunador) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByEmailAndPasswordAndCodigo(validarVacunador.getEmail(), validarVacunador.getPassword(), validarVacunador.getCodigo());
		if(vacunadorBuscado != null) {
			VacunadorDTO vacunadorDTO = this.mapearVacunador(vacunadorBuscado);
			return vacunadorDTO;
		}
		else {			
			return null;
		}
	}

	public Boolean devolverSiExisteCodigoVacunadorEnTabla(Integer codigo) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByCodigo(codigo);
		if(vacunadorBuscado != null) {
			return true;
		}
		else {			
			return null;
		}
	}

	public List<Integer> getCodigosVacunadores() {
		List<Vacunador> vacunadores = vacunadorRepository.findAll();
		List<Integer> response = new ArrayList<>();
		if(vacunadores!=null) {
			for(Vacunador vacun : vacunadores) {
				response.add(vacun.getCodigo());
			}
			return response;
		}
		else {			
			return null;
		}
	}

	public List<String> getMailsVacunadores() {
		List<Vacunador> vacunadores = vacunadorRepository.findAll();
		List<String> response = new ArrayList<>();
		if(vacunadores!=null) {
			for(Vacunador vacun : vacunadores) {
				response.add(vacun.getEmail());
			}
			return response;
		}
		else {			
			return null;
		}
	}

	public List<Integer> getDnisVacunadores() {
		List<Vacunador> vacunadores = vacunadorRepository.findAll();
		List<Integer> response = new ArrayList<>();
		if(vacunadores!=null) {
			for(Vacunador vacun : vacunadores) {
				response.add(vacun.getDni());
			}
			return response;
		}
		else {			
			return null;
		}
	}
	
	public Boolean editarVacunador(String nombre, String apellido, String password, Integer idZona, Integer dni) {
        Zona zona = zonaRepository.findById(idZona).get();
        Vacunador vacunadorBuscado = vacunadorRepository.findByDni(dni);
        if(vacunadorBuscado != null) {
            if(!vacunadorBuscado.getNombre().trim().equals(nombre) && !nombre.isBlank()) {    //si nombre no es = a el nombre original y si no es blank, cambia el nombre
                vacunadorBuscado.setNombre(nombre);
            }
            if(!vacunadorBuscado.getApellido().trim().equals(apellido) && !apellido.isBlank()) {    //si apellido no es = a el apellido original y si no es blank, cambia el apellido
                vacunadorBuscado.setApellido(apellido);
            }
            if(!vacunadorBuscado.getPassword().equals(password) && !password.isBlank()) {    //si password no es = a la password original y si no es blank, cambia la passsword
                vacunadorBuscado.setPassword(password);
            }

//            VacunadorZona ultZona = vacunadorBuscado.getZonas().stream().max(Comparator.comparingInt(zo -> zo.getId())).get();    //obtiene ultima zona asignada al vacunador (su zona actual)

//            if(!ultZona.getZona().getNombreZona().equals(zona.getNombreZona())) {    // si zona no es = a la asignada actualmente, lo cambia de zona
//                VacunadorZona aux = new VacunadorZona(vacunadorBuscado, zona);
//                vacunadorBuscado.getZonas().add(aux);    //agrega la nueva zona al set, la nueva zona queda cn el mayor id
//            }
            
            if(!vacunadorBuscado.getZona().getNombreZona().equals(zona.getNombreZona())) {    // si zona no es = a la asignada actualmente, lo cambia de zona
              vacunadorBuscado.setZona(zona);
          }
            
            vacunadorRepository.save(vacunadorBuscado); //actualiza los datos
            return true;
        } else {
            return false;
        }
    }

	public VacunadorDTO editarVacunadorObject(String nombre, String apellido, String password, Integer idZona,
			Integer dni) {
		Zona zona = zonaRepository.findById(idZona).get();
        Vacunador vacunadorBuscado = vacunadorRepository.findByDni(dni);
        if(vacunadorBuscado != null) {
            if(!vacunadorBuscado.getNombre().trim().equals(nombre) && !nombre.isBlank()) {    //si nombre no es = a el nombre original y si no es blank, cambia el nombre
                vacunadorBuscado.setNombre(nombre);
            }
            if(!vacunadorBuscado.getApellido().trim().equals(apellido) && !apellido.isBlank()) {    //si apellido no es = a el apellido original y si no es blank, cambia el apellido
                vacunadorBuscado.setApellido(apellido);
            }
            if(!vacunadorBuscado.getPassword().equals(password) && !password.isBlank()) {    //si password no es = a la password original y si no es blank, cambia la passsword
                vacunadorBuscado.setPassword(password);
            }

//            VacunadorZona ultZona = vacunadorBuscado.getZonas().stream().max(Comparator.comparingInt(zo -> zo.getId())).get();    //obtiene ultima zona asignada al vacunador (su zona actual)

//            if(!ultZona.getZona().getNombreZona().equals(zona.getNombreZona())) {    // si zona no es = a la asignada actualmente, lo cambia de zona
//                VacunadorZona aux = new VacunadorZona(vacunadorBuscado, zona);
//                vacunadorBuscado.getZonas().add(aux);    //agrega la nueva zona al set, la nueva zona queda cn el mayor id
//            }
            
            if(!vacunadorBuscado.getZona().getNombreZona().equals(zona.getNombreZona())) {    // si zona no es = a la asignada actualmente, lo cambia de zona
              vacunadorBuscado.setZona(zona);
          }
            VacunadorDTO vacunadorResponse = this.mapearVacunador(vacunadorBuscado);
            vacunadorRepository.save(vacunadorBuscado); //actualiza los datos
            return vacunadorResponse;
        } else {
            return null;
        }
	}

	
	public List<VacunadorDTO> devolverVacunadoresEnZona(Integer zonaId) {
		List<Object[]> query = vacunadorRepository.getVacunadoresEnZona(zonaId);
		List<VacunadorDTO> response = new ArrayList<>();
		for(Object[] vacunDB : query) {
			Vacunador vacunadorBuscado = vacunadorRepository.findByDni((Integer) vacunDB[1]);
			if(vacunadorBuscado!= null) {
				response.add(this.mapearVacunador(vacunadorBuscado));
			}
		}
		return (response.size() == 0 ?  null :  response);
	}
	
	
	// HACK para editar
	public String getPassword(Integer dni) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByDni(dni);
		if(vacunadorBuscado!= null) {
			return vacunadorBuscado.getPassword();
		}
		return null;
	}

	public Boolean deleteVacunador(Integer vacunadorId) {
		Vacunador vacunadorBuscado = vacunadorRepository.findById(vacunadorId).get();
		if(vacunadorBuscado!= null) {
			vacunadorRepository.deleteById(vacunadorId);
			return true;
		}
		return false;
	}

	public Boolean registrarInasistenciaTurno(Integer turnoId) throws MessagingException {
		Turno turnoBuscado = turnoRepository.findById(turnoId).get();
		
		// No asistio --> False
		turnoBuscado.setAsistio(false);
		turnoRepository.saveAndFlush(turnoBuscado);
		
		Paciente pacienteAsignado = turnoBuscado.getPaciente();
		Vacuna vacuna = turnoBuscado.getVacuna();
		Vacunatorio vacunatorio = turnoBuscado.getVacunatorio();
		
		if(!pacienteAsignado.getEsDeRiesgo()) {
			// Covid sin riesgo --> Solo elimino turno
			if(turnoBuscado.getVacuna().getId() == 1 || turnoBuscado.getVacuna().getId() == 2 || turnoBuscado.getVacuna().getId() == 3) {
				//MANDAR MAIL
				emailSenderService.sendMailCovidCasiVacio(pacienteAsignado, vacuna.getNombre());
			}
			// Gripe sin riesgo --> 1 MES
			else if (turnoBuscado.getVacuna().getId() == 4) {
				LocalDate fecha = turnoBuscado.getFechaAplicacion().toLocalDate();
				fecha = fecha.plusMonths(1);
				LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha, vacunatorio.getId());
					
				// Asignar nuevo turno
				Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, null, 
						vacuna, vacunatorio, pacienteAsignado);
				turnoRepository.saveAndFlush(nuevoTurno);
				
				//MANDAR MAIL
				String fechaNueva = primerTurnoLibre.toLocalDate().toString();
				String horaNueva = primerTurnoLibre.toLocalTime().toString();
				emailSenderService.sendMailWithoutAttachment(pacienteAsignado, vacuna.getNombre(), fechaNueva, horaNueva);
			}
			
			// Caso para la fiebre amarilla
			else if (turnoBuscado.getVacuna().getId() == 5) {
				
				Solicitud solicitudBuscada = pacienteAsignado.getSolicitud();
				solicitudRepository.delete(solicitudBuscada);

				//MANDAR MAIL
				emailSenderService.sendMailAmarillaInasistencia(pacienteAsignado, vacuna.getNombre());
			}
			
			// Caso para la fiebre amarilla
		}
		else {
			// Covid o gripe con riesgo --> 1 SEMANA
			if(turnoBuscado.getVacuna().getId() == 1 || turnoBuscado.getVacuna().getId() == 2 ||
					turnoBuscado.getVacuna().getId() == 3 || turnoBuscado.getVacuna().getId() == 4) {
				LocalDate fecha = turnoBuscado.getFechaAplicacion().toLocalDate();
				fecha = fecha.plusWeeks(1);
				LocalDateTime primerTurnoLibre = this.buscarTurnoLibre(fecha, vacunatorio.getId());
				
				// Asignar nuevo turno
				Turno nuevoTurno = new Turno(0, LocalDateTime.of(LocalDate.now(), LocalTime.now()), primerTurnoLibre, null, 
						vacuna, vacunatorio, pacienteAsignado);
				turnoRepository.saveAndFlush(nuevoTurno);
				
				//MANDAR MAIL
				String fechaNueva = primerTurnoLibre.toLocalDate().toString();
				String horaNueva = primerTurnoLibre.toLocalTime().toString();
				emailSenderService.sendMailWithoutAttachment(pacienteAsignado, vacuna.getNombre(), fechaNueva, horaNueva);
			}
			
			// Caso para la fiebre amarilla
			else if (turnoBuscado.getVacuna().getId() == 5) {
				
				Solicitud solicitudBuscada = pacienteAsignado.getSolicitud();
				solicitudRepository.delete(solicitudBuscada);

				//MANDAR MAIL
				emailSenderService.sendMailAmarillaInasistencia(pacienteAsignado, vacuna.getNombre());
			}
		}
		return true;
	}
	
	public Boolean registrarAsistenciaTurno(Integer turnoId) throws MessagingException {
		Turno turnoBuscado = turnoRepository.findById(turnoId).get();
		
		// No asistio --> False
		turnoBuscado.setAsistio(true);
		turnoRepository.saveAndFlush(turnoBuscado);
		
		Paciente pacienteAsignado = turnoBuscado.getPaciente();
		Vacuna vacuna = turnoBuscado.getVacuna();
		
		//MENOS 1 DE STOCK
		VacunatorioVacuna vacunatorioVacuna = vacunatorioVacunaRepository.findByVacunatorioAndVacuna(pacienteAsignado.getZona().getVacunatorio(),
				vacuna);
		vacunatorioVacuna.setStock(vacunatorioVacuna.getStock() - 1);
		vacunatorioVacunaRepository.save(vacunatorioVacuna);
		
		//MANDAR MAIL
		String fechaAplicada = turnoBuscado.getFechaAplicacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String horaAplicada = turnoBuscado.getFechaAplicacion().format(DateTimeFormatter.ofPattern("HH:mm"));
		emailSenderService.sendMailAsistenciaTurno(pacienteAsignado, vacuna.getNombre(), fechaAplicada, horaAplicada);
		return true;
	}
	
	// Busca el primer turno libre a partir de la fecha ingresada.
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
						System.out.println("Encontre");
						encontre = true;
						fechaLibre = fecha;
						horaLibre = horaInicio;
					}
					horaInicio = horaInicio.plusMinutes(15);
				}
			}
			// Es un finde || Termine la jornada --> Sumo un dia para seguir loopeando
			fecha = fecha.plusDays(1);
		}
		return LocalDateTime.of(fechaLibre, horaLibre);
	}

	public Boolean testtt(Integer turnoId) throws MessagingException {
		Turno turnoBuscado = turnoRepository.findById(turnoId).get();
		Paciente pacienteAsignado = turnoBuscado.getPaciente();
		Vacuna vacuna = turnoBuscado.getVacuna();
		String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		emailSenderService.sendMailCovidCasiVacio(pacienteAsignado, vacuna.getNombre());
		return true;
	}


}

