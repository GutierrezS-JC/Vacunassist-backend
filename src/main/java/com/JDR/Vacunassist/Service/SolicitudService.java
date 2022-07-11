package com.JDR.Vacunassist.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.RechazarSolicitudRequest;
import com.JDR.Vacunassist.Dto.SolicitudDTO;
import com.JDR.Vacunassist.Model.Administrador;
import com.JDR.Vacunassist.Model.Paciente;
import com.JDR.Vacunassist.Model.Solicitud;
import com.JDR.Vacunassist.Repository.AdministradorRepository;
import com.JDR.Vacunassist.Repository.PacienteRepository;
import com.JDR.Vacunassist.Repository.SolicitudRepository;

@Service
public class SolicitudService {

	@Autowired
	SolicitudRepository solicitudRepository;
	
	@Autowired
	AdministradorRepository administradorRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	public List<SolicitudDTO> getSolicitudes() {
		List<Object[]> listaDB = solicitudRepository.getSolicitudes();
		List<SolicitudDTO> listaResponse = new ArrayList<>();
		if(listaDB != null) {
			listaResponse = this.mapearListaSolicitud(listaDB);
		}
		return listaResponse;
	}

	public List<SolicitudDTO> getSolicitudesAprobadas() {
		List<Object[]> listaDB = solicitudRepository.getSolicitudesAprobadas();
		List<SolicitudDTO> listaResponse = new ArrayList<>();
		if(listaDB != null) {
			listaResponse = this.mapearListaSolicitud(listaDB);
		}
		return listaResponse;
	}
	
	public List<SolicitudDTO> getSolicitudesRechazadas() {
		List<Object[]> listaDB = solicitudRepository.getSolicitudesRechazadas();
		List<SolicitudDTO> listaResponse = new ArrayList<>();
		if(listaDB != null) {
			listaResponse = this.mapearListaSolicitud(listaDB);
		}
		return listaResponse;
	}
	
	public List<SolicitudDTO> getSolicitudesPendientes() {
		List<Object[]> listaDB = solicitudRepository.getSolicitudesPendientes();
		List<SolicitudDTO> listaResponse = new ArrayList<>();
		if(listaDB != null) {
			listaResponse = this.mapearListaSolicitud(listaDB);
		}
		return listaResponse;
	}
	
	private List<SolicitudDTO> mapearListaSolicitud(List<Object[]> listaDB) {
		List<SolicitudDTO> listaResponse = new ArrayList<>();
		for(Object[] objectDB : listaDB) {
			SolicitudDTO nuevaSolicitud = new SolicitudDTO((Integer)objectDB[0], (Boolean)objectDB[1],
					LocalDate.parse(objectDB[2].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					LocalDate.parse(objectDB[3].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					(Integer)objectDB[4], (String)objectDB[5], (String)objectDB[6], (Integer)objectDB[7],
					(Integer)objectDB[8], (String)objectDB[9], (String)objectDB[10]);
			listaResponse.add(nuevaSolicitud);
		}
		return listaResponse;
	}

	public Boolean rechazarSolicitud(RechazarSolicitudRequest request) {
		Solicitud solicitudBuscada = solicitudRepository.findById(request.getSolicitudId()).get();
		Administrador adminBuscado = administradorRepository.findById(request.getAdminId()).get();
		solicitudBuscada.setAprobado(false);
		solicitudBuscada.setAdministrador(adminBuscado);
		solicitudRepository.save(solicitudBuscada);
		return true;
	}

	public Boolean resetSolicitudFiebreAmarilla(Integer pacienteId) {
		System.out.println("Reset");
		Paciente pacienteBuscado = pacienteRepository.findById(pacienteId).orElse(null);
		if(pacienteBuscado != null) {
			Solicitud solicitudBuscada = pacienteBuscado.getSolicitud();
			System.out.println(solicitudBuscada);
			if(solicitudBuscada != null ) {
				solicitudRepository.delete(solicitudBuscada);	
				pacienteBuscado.setSolicitud(null);
				pacienteRepository.saveAndFlush(pacienteBuscado);
				return true;
			}
		}
		return false;
	}

}
