package com.JDR.Vacunassist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JDR.Vacunassist.Model.Administrador;
import com.JDR.Vacunassist.Repository.AdministradorRepository;

@RestController
public class AdministradorController {

	@Autowired 
	private AdministradorRepository administradorRepository;
	
	@GetMapping("/getAdministradores")
	public List<Administrador> getAdministradores(){
		return administradorRepository.findAll();
	}
}
