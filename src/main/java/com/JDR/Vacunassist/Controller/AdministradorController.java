package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.AdministradorDTO;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Service.AdministradorService;

@RestController
public class AdministradorController {
	
	@Autowired
	private AdministradorService administradorService;
	
	@GetMapping("/getAdministradores")
	public List<AdministradorDTO> getAdministradores(){
		return administradorService.devolverAdministradores();
	}
	
	@GetMapping("/getAdministrador/{id}")
	public ResponseEntity<AdministradorDTO> getAdministradorPorId(@PathVariable(name="id") Integer id) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.devolverAdminPorId(id));
	}
	
	@GetMapping("/getAdministradorByDni/{dni}")
	public ResponseEntity<AdministradorDTO> getAdministradorPorDNI(@PathVariable(name="dni") Integer dni) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.devolverAdminPorDNI(dni));
	}
	
	//Test - Funciona
	@GetMapping("/getBoolean/{id}")
	public ResponseEntity<Boolean> getBoolean(@PathVariable(name="id") Integer id) {
		if(id.intValue() == 1) {			
			return ResponseEntity.ok(true);
		}
		else {
			return ResponseEntity.ok(false);
		}
	}
}
