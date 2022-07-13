package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.PacienteDTO;
import com.JDR.Vacunassist.Dto.PacienteRequest;
import com.JDR.Vacunassist.Dto.SolicitudFiebreAmarilla;
import com.JDR.Vacunassist.Dto.SolicitudStatus;
import com.JDR.Vacunassist.Dto.TurnosPacienteResponse;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunasPacienteResponse;
import com.JDR.Vacunassist.Dto.ValidarPaciente;
import com.JDR.Vacunassist.Dto.ValidarVacunador;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Repository.PacienteRepository;
import com.JDR.Vacunassist.Service.PacienteService;

@RestController
@CrossOrigin
public class PacienteController {

	@Autowired
	PacienteService pacienteService;
	
	@GetMapping("/getPacientes")
	public List<PacienteDTO> getPacientes(){
		return pacienteService.getPacientes();
	}
	
	@GetMapping("/getPacienteByDni/{dni}")
	public List<PacienteDTO> getPacientePorDni(@PathVariable(name="dni") Integer dni) throws ResourceNotFoundException{
		return pacienteService.devolverPacientePorDni(dni);
	}
	
	@GetMapping("/getPacientesEnZona")
	public List<PacienteDTO> getPacientesEnZona(@RequestParam("zonaId") Integer zonaId){
		return pacienteService.devolverPacientesEnZona(zonaId);
	}
	
	@GetMapping("/getDnisPacientes")
	public List<Integer> getDnisPacientes(){
		return pacienteService.getDnisPacientes();
	}
	
	@GetMapping("/getEmailsPacientes")
	public List<String> getEmailsPacientes(){
		return pacienteService.getEmailsPacientes();
	}
	
	@GetMapping("/getPasswordPaciente")
	public ResponseEntity<String> getPasswordPaciente(@RequestParam("dni") Integer dni) {
		return ResponseEntity.ok(pacienteService.getPasswordPaciente(dni));
	}
	
	@GetMapping("/getTurnosPaciente")
	public List<TurnosPacienteResponse> getTurnosPaciente(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return pacienteService.getTurnosPaciente(pacienteId);
	}
	
	@GetMapping("/getVacunasPaciente")
	public List<VacunasPacienteResponse> getVacunasPaciente(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return pacienteService.getVacunasPaciente(pacienteId);
	}
	
	@GetMapping("/getVacunasCovidPaciente")
	public VacunasPacienteResponse getVacunasCovidPaciente(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return pacienteService.getVacunasCovidPaciente(pacienteId);
	}
	
	@GetMapping("/getVacunasGripePaciente")
	public VacunasPacienteResponse getVacunasGripePaciente(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return pacienteService.getVacunasGripePaciente(pacienteId);
	}
	
	@GetMapping("/getVacunasFiebreAmarillaPaciente")
	public VacunasPacienteResponse getVacunasFiebreAmarillaPaciente(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return pacienteService.getVacunasFiebreAmarillaPaciente(pacienteId);
	}
	
	@GetMapping("/getTieneSolicitudFiebreAmarillaPaciente")
	public Boolean getTieneSolicitudFiebreAmarillaPaciente(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return pacienteService.getTieneSolicitudFiebreAmarillaPaciente(pacienteId);
	}
	
	@GetMapping("/getTieneSolicitudFiebreAmarillaPacienteV2")
	public SolicitudStatus getTieneSolicitudFiebreAmarillaPacienteV2(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return pacienteService.getTieneSolicitudFiebreAmarillaPacienteV2(pacienteId);
	}
	
	@PostMapping("/solicitarTurnoFiebreAmarilla")
	public ResponseEntity<Boolean> solicitarTurnoFiebreAmarilla(@RequestBody SolicitudFiebreAmarilla solicitudRequest) throws ResourceNotFoundException{
		return ResponseEntity.ok(pacienteService.solicitarTurnoFiebreAmarilla(solicitudRequest));
	}
	
	// THIS FIRST FOR LOGIN
	@PostMapping("/validarPacienteBooleanPost")
	public ResponseEntity<Boolean> validarPacienteBooleanPost(@RequestBody ValidarPaciente validarPaciente) throws ResourceNotFoundException{
		return ResponseEntity.ok(pacienteService.validarPacienteBoolean(validarPaciente));
	}
	
	// THIS SECOND FOR LOGIN 
	@PostMapping("/validarPacientePost")
	public ResponseEntity<PacienteDTO> validarPacientePost(@RequestBody ValidarPaciente validarPaciente) throws ResourceNotFoundException{
		return ResponseEntity.ok(pacienteService.validarPacienteConCodigo(validarPaciente));
	}
	
	@PostMapping("/cargarPaciente")
	public ResponseEntity<Integer> cargarPaciente(@RequestBody PacienteRequest pacienteRequest ){
		return ResponseEntity.ok(pacienteService.cargarPaciente(pacienteRequest));
	}
	
	
	@DeleteMapping("/deletePaciente")
	public ResponseEntity<Boolean> deletePaciente(@RequestParam("pacienteId") Integer pacienteId) throws ResourceNotFoundException{
		return ResponseEntity.ok(pacienteService.deletePaciente(pacienteId));
	}
	
	@PutMapping("/editarPacienteObject")
	public PacienteDTO editarPacienteObject(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
			@RequestParam("password") String password, @RequestParam("idZona") Integer idZona, @RequestParam("dni") Integer dni, @RequestParam("deRiesgo") Boolean deRiesgo){
		return pacienteService.editarPacienteObject(nombre, apellido, password, idZona, dni, deRiesgo);
	}
	
	// REASIGNAR
	@PostMapping("/reasignarTurno")
	public Boolean reasignarTurno(@RequestParam("turnoId") Integer turnoId ){
		return pacienteService.reasignarTurno(turnoId);
	}
}
