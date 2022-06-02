package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Model.DniValido;
import com.JDR.Vacunassist.Service.DniValidoService;

@RestController
@CrossOrigin
public class DniValidoController {

	@Autowired
	DniValidoService dniValidoService;
	
	@GetMapping("/getDniValidos")
	public List<DniValido> getDniValidos(){
		return dniValidoService.devolverDniValidos();
	}
	
	@GetMapping("/getDniIngresadoEsValido")
	public ResponseEntity<Boolean> getDniIngresadoEsValido(@RequestParam("dni") Integer dni){
		return ResponseEntity.ok(dniValidoService.devolverDniIngresadoEsValido(dni));
	}
	
	@PostMapping("/cargarDniValido")
	public ResponseEntity<DniValido> cargarDniValido(@RequestBody DniValido dniValido ){
		return ResponseEntity.ok(dniValidoService.cargarDniValido(dniValido));
	}
	
}
