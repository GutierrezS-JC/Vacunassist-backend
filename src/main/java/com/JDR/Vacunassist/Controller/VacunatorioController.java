package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.AdministradorDTO;
import com.JDR.Vacunassist.Dto.VacunatorioDTO;
import com.JDR.Vacunassist.Dto.VacunatorioFullDTO;
import com.JDR.Vacunassist.Dto.VacunatorioPost;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Model.DniValido;
import com.JDR.Vacunassist.Service.VacunatorioService;

@RestController
public class VacunatorioController {

	@Autowired
	VacunatorioService vacunatorioService;
	
	@GetMapping("/getVacunatorios")
	public List<VacunatorioFullDTO> getVacunatorios(){
		return vacunatorioService.devolverVacunatorios();
	}
	
	@GetMapping("/getVacunatorio/{id}")
	public ResponseEntity<VacunatorioFullDTO> getVacunatorioPorId(@PathVariable(name="id") Integer id) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunatorioService.devolverVacunatorioPorId(id));
	}
	
	@GetMapping("/getVacunatorioPorNombre")
	public ResponseEntity<VacunatorioFullDTO> getVacunatorioPorNombre(@RequestParam("nombre") String nombre) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunatorioService.devolverVacunatorioPorNombre(nombre));
	}
	
	@PostMapping("/cargarVacunatorio")
	public ResponseEntity<VacunatorioFullDTO> cargarVacunatorio(@RequestBody VacunatorioPost vacunatorioPost ){
		return ResponseEntity.ok(vacunatorioService.cargarVacunatorio(vacunatorioPost));
	}
	
	//Insertar vacunas al centro de vacunacion (no agregar stock)
	@PostMapping("/cargarVacunaEnVacunatorio")
	public ResponseEntity<Boolean> cargarVacuna(@RequestParam("vacunatorioId") Integer vacunatorioId, @RequestParam("vacunaId") Integer vacunaId){
		return ResponseEntity.ok(vacunatorioService.cargarVacuna(vacunatorioId, vacunaId));
	}
	
	//Insertar vacunas al centro de vacunacion (no agregar stock)
	@PostMapping("/actualizarStock")
	public ResponseEntity<Integer> actualizarStock(@RequestParam("vacunatorioId") Integer vacunatorioId, @RequestParam("vacunaId") Integer vacunaId, @RequestParam("stock") Integer stock){
		return ResponseEntity.ok(vacunatorioService.actualizarStock(vacunatorioId, vacunaId, stock));
	}
	
	@PutMapping("/editarNombreVacunatorio")
	public ResponseEntity<Boolean> updateNombreVacunatorio(@RequestParam("nombre") String nombre, @RequestParam("id") Integer id) throws ResourceNotFoundException{
		return ResponseEntity.ok(vacunatorioService.editarNombreVacunatorio(nombre,id));
	}

}
