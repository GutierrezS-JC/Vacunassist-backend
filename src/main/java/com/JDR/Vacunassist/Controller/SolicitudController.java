package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.RechazarSolicitudRequest;
import com.JDR.Vacunassist.Dto.SolicitudDTO;
import com.JDR.Vacunassist.Service.SolicitudService;

@RestController
@CrossOrigin
public class SolicitudController {

	@Autowired
	SolicitudService solicitudService;
	
	@GetMapping("/getSolicitudes")
	public List<SolicitudDTO> getSolicitudes(){
		return solicitudService.getSolicitudes();
	}

	@GetMapping("/getSolicitudesAprobadas")
	public List<SolicitudDTO> getSolicitudesAprobadas(){
		return solicitudService.getSolicitudesAprobadas();
	}
	
	@GetMapping("/getSolicitudesRechazadas")
	public List<SolicitudDTO> getSolicitudesRechazadas(){
		return solicitudService.getSolicitudesRechazadas();
	}
	
	@GetMapping("/getSolicitudesPendientes")
	public List<SolicitudDTO> getSolicitudesPendientes(){
		return solicitudService.getSolicitudesPendientes();
	}
	
	@PostMapping("/rechazarSolicitud")
	public Boolean rechazarSolicitud(@RequestBody RechazarSolicitudRequest requestRechazo){
		return solicitudService.rechazarSolicitud(requestRechazo);
	}
	
	@PostMapping("/resetSolicitudFiebreAmarilla")
	public Boolean resetSolicitudFiebreAmarilla(@RequestParam("pacienteId") Integer pacienteId){
		return solicitudService.resetSolicitudFiebreAmarilla(pacienteId);
	}
	
}
