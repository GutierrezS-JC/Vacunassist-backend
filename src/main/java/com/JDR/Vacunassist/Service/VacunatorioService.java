package com.JDR.Vacunassist.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.VacunatorioFullDTO;
import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Model.Vacunatorio;
import com.JDR.Vacunassist.Repository.VacunatorioRepository;

@Service
public class VacunatorioService {
	
	@Autowired
	VacunatorioRepository vacunatorioRepository;

	public List<VacunatorioFullDTO> devolverVacunatorios() {
		List<Vacunatorio> vacunatorioList = vacunatorioRepository.findAll();
		List<VacunatorioFullDTO> response = this.convertirVacunatorio(vacunatorioList);
		return null;
	}

	private List<VacunatorioFullDTO> convertirVacunatorio(List<Vacunatorio> vacunatorioList) {
		return vacunatorioList.stream().map(vacunatorio -> mapearVacunatorio(vacunatorio)).collect(Collectors.toList());
	}

	public VacunatorioFullDTO devolverVacunatorioPorId(Integer id) {
		Vacunatorio vacunatorio = vacunatorioRepository.findById(id).get();
		return mapearVacunatorio(vacunatorio);
	}

	public VacunatorioFullDTO devolverVacunatorioPorNombre(String nombre) {
		Vacunatorio vacunatorio = vacunatorioRepository.findByNombre(nombre).get();
		return mapearVacunatorio(vacunatorio);
	}
	
	private VacunatorioFullDTO mapearVacunatorio(Vacunatorio vacunatorio) {
		ZonaDTO zonaDto = new ZonaDTO(vacunatorio.getZona().getId(), vacunatorio.getZona().getNombreZona());
		VacunatorioFullDTO vacunatorioDTO = new VacunatorioFullDTO(vacunatorio.getId(),vacunatorio.getNombre(), vacunatorio.getCalle(), vacunatorio.getAltura(), zonaDto );
		return vacunatorioDTO;
	}


}
