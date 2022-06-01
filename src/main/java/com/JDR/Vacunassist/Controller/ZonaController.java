package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Service.ZonaService;

@RestController
public class ZonaController {

	@Autowired
	ZonaService zonaService;
	
	@GetMapping("/getZonas")
	public List<ZonaDTO> getZonas(){
		return zonaService.devolverZonas();
	}
	
	@PostMapping("/cargarZona")
	public ResponseEntity<ZonaDTO> cargarZona(@RequestBody ZonaDTO zonaDTO ){
		return ResponseEntity.ok(zonaService.cargarZona(zonaDTO));
	}
}
