package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.AdministradorDTO;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.ValidarAdmin;
import com.JDR.Vacunassist.Dto.ValidarVacunador;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Service.AdministradorService;

@RestController
@CrossOrigin
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
	public ResponseEntity<AdministradorDTO> getAdministradorPorDni(@PathVariable(name="dni") Integer dni) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.devolverAdminPorDNI(dni));
	}
	
	@GetMapping("/getExisteDniAdministrador")
	public ResponseEntity<Boolean> getExisteDniAdministrador(@RequestParam("dni") Integer dni) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.devolverSiExisteDniEnAdminTable(dni));
	}
	
	@GetMapping("/validarAdmin")
	public ResponseEntity<AdministradorDTO> validarAdmin(@RequestBody ValidarAdmin validarAdmin) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.validarAdmin(validarAdmin));
	}
	
	//Con codigo, recupero objeto -- THIS SECOND
	@PostMapping("/validarAdminEsPost")
	public ResponseEntity<AdministradorDTO> validarAdminEsPost(@RequestBody ValidarAdmin validarAdmin) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.validarAdmin(validarAdmin));
	}
	
	
	@GetMapping("/validarAdminBoolean")
	public ResponseEntity<Boolean> validarAdminBoolean(@RequestBody ValidarAdmin validarAdmin) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.validarAdminBoolean(validarAdmin));
	}
	
	//Antes de ingresar el codigo -- THIS FIRST
	@PostMapping("/validarAdminBooleanPost")
	public ResponseEntity<Boolean> validarAdminBooleanPost(@RequestBody ValidarAdmin validarAdmin) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.validarAdminBoolean(validarAdmin));
	}
	
	@GetMapping("/validarAdminConCodigo")
	public ResponseEntity<AdministradorDTO> validarAdminConCodigo(@RequestBody ValidarAdmin validarAdmin) throws ResourceNotFoundException{
		return ResponseEntity.ok(administradorService.validarAdminConCodigo(validarAdmin));
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
