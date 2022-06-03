package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunadorRequest;
import com.JDR.Vacunassist.Dto.ValidarVacunador;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Service.VacunadorService;

@RestController
@CrossOrigin
public class VacunadorController {
	
	@Autowired 
	VacunadorService vacunadorService;
	
	@GetMapping("/getVacunadores")
	public List<VacunadorDTO> getVacunadores(){
		return vacunadorService.devolverVacunadores();
	}
	
	@GetMapping("/getExisteCodigoVacunador")
	public ResponseEntity<Boolean> getExisteCodigoVacunador(@RequestParam("codigo") Integer codigo) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.devolverSiExisteCodigoVacunadorEnTabla(codigo));
	}
	//=======================================================================//
	
	//For Register Only and Only Okk - Registro Vacunador
	@GetMapping("/getCodigosVacunadores")
	public List<Integer> getCodigosVacunadores(){
		return vacunadorService.getCodigosVacunadores();
	}
	
	@GetMapping("/getMailsVacunadores")
	public List<String> getMailsVacunadores(){
		return vacunadorService.getMailsVacunadores();
	}
	
	@GetMapping("/getDnisVacunadores")
	public List<Integer> getDnisVacunadores(){
		return vacunadorService.getDnisVacunadores();
	}
	
	//=============================================================//
	
	@GetMapping("/getVacunadoresEnRango")
	public List<VacunadorDTO> getVacunadoresEnRango(@RequestParam("inferiorDni") Integer inferiorDni, @RequestParam("superiorDni") Integer superiorDni){
		return vacunadorService.devolverVacunadoresEnRango(inferiorDni, superiorDni);
	}
	
//	@GetMapping("/getVacunadoresEnZona")
//	public List<VacunadorDTO> getVacunadoresEnZona(@RequestParam("zonaId") Integer zonaId){
//		return vacunadorService.devolverVacunadoresEnZona(zonaId);
//	}

	@GetMapping("/validarVacunador")
	public ResponseEntity<VacunadorDTO> validarVacunador(@RequestBody ValidarVacunador validarVacunador) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.validarVacunador(validarVacunador));
	}
	
	//Devuelvo true si existe sino false
	@GetMapping("/validarVacunadorBoolean")
	public ResponseEntity<Boolean> validarVacunadorBoolean(@RequestBody ValidarVacunador validarVacunador) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.validarVacunadorBoolean(validarVacunador));
	}
	
	@GetMapping("/validarVacunadorConCodigo")
	public ResponseEntity<VacunadorDTO> validarVacunadorConCodigo(@RequestBody ValidarVacunador validarVacunador) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.validarVacunadorConCodigo(validarVacunador));
	}
	
	@GetMapping("/getVacunador/{id}")
	public ResponseEntity<VacunadorDTO> getVacunadorPorId(@PathVariable(name="id") Integer id) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.devolverVacunadorPorId(id));
	}
	
	@GetMapping("/getVacunadorByDni/{dni}")
	public ResponseEntity<VacunadorDTO> getVacunadorPorDni(@PathVariable(name="dni") Integer dni) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.devolverVacunadorPorDni(dni));
	}
	
	@GetMapping("/getExisteDniVacunador")
	public ResponseEntity<Boolean> getExisteDniVacunador(@RequestParam("dni") Integer dni) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunadorService.devolverSiExisteDniEnVacunadorTable(dni));
	}
	
	@PostMapping("/cargarVacunador")
	public ResponseEntity<VacunadorDTO> cargarVacunador(@RequestBody VacunadorRequest vacunadorRequest ){
		return ResponseEntity.ok(vacunadorService.cargarVacunador(vacunadorRequest));
	}
}
