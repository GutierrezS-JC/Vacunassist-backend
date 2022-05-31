package com.JDR.Vacunassist.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.AdministradorDTO;
import com.JDR.Vacunassist.Model.DniValido;
import com.JDR.Vacunassist.Repository.DniValidoRepository;

@Service
public class DniValidoService {

	@Autowired
	DniValidoRepository dniValidoRepository;

	public List<DniValido> devolverDniValidos() {
		List<DniValido> listaValidos = dniValidoRepository.findAll();
		return listaValidos;
	}

	public Boolean devolverDniIngresadoEsValido(Integer dni) {
		DniValido dniValido = dniValidoRepository.findByDni(dni);
		if(dniValido != null) return true; else return false;
	}

	public DniValido cargarDniValido(DniValido dniValido) {
		DniValido dniValidoBuscado = dniValidoRepository.findByDni(dniValido.getDni());
		if(dniValidoBuscado != null) {
			return null;
		}
		else{
			DniValido nuevoDni = dniValidoRepository.save(new DniValido(dniValido.getId(),dniValido.getDni(), dniValido.getNombre(), dniValido.getApellido()));
			return nuevoDni;
		}
	}
	
	
}
