package com.JDR.Vacunassist.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.VacunaStockDTO;
import com.JDR.Vacunassist.Dto.VacunatorioVacunaDTO;
import com.JDR.Vacunassist.Model.Vacunatorio;
import com.JDR.Vacunassist.Model.VacunatorioVacuna;
import com.JDR.Vacunassist.Repository.VacunatorioRepository;
import com.JDR.Vacunassist.Repository.VacunatorioVacunaRepository;

@Service
public class VacunatorioVacunaService {

	@Autowired
	VacunatorioVacunaRepository vacunatorioVacunaRepository;
	
	@Autowired 
	VacunatorioRepository vacunatorioRepository;

	public VacunatorioVacunaDTO devolverUnVacunatorioVacunas(Integer vacunatorioId) {
		Vacunatorio vacunatorioBuscado = vacunatorioRepository.findById(vacunatorioId).get();

		if(vacunatorioBuscado != null) {
			List<VacunaStockDTO> listaStock = new ArrayList<>();
			Set<VacunatorioVacuna> listaVacunatorioVacuna = vacunatorioBuscado.getVacunas();
			for (VacunatorioVacuna vacunatorioVacuna : listaVacunatorioVacuna) {
				listaStock.add(new VacunaStockDTO(vacunatorioVacuna.getVacuna().getId(), vacunatorioVacuna.getVacuna().getNombre(), vacunatorioVacuna.getFecha(), vacunatorioVacuna.getStock()));
			}
			
			VacunatorioVacunaDTO response = new VacunatorioVacunaDTO(vacunatorioBuscado.getId(),vacunatorioBuscado.getNombre(), listaStock);
			return response;
		}
		else {
			return null;			
		}
	}
	
	public List<VacunatorioVacunaDTO> devolverAllVacunatoriosVacunas() {
		List<Vacunatorio> listaVacunatorios = vacunatorioRepository.findAll();
		//List<VacunatorioVacuna> listaVacunatoriosVacunas = vacunatorioVacunaRepository.findAll();
		List<VacunatorioVacunaDTO> response = this.convertirVacunatoriosVacunas(listaVacunatorios);
		return response;
	}

	
	
	private List<VacunatorioVacunaDTO> convertirVacunatoriosVacunas(List<Vacunatorio> listaVacunatoriosVacunas) {
		return listaVacunatoriosVacunas.stream().map(vacunatorio -> mapearVacunatorioVacuna(vacunatorio)).collect(Collectors.toList());
	}

	private VacunatorioVacunaDTO mapearVacunatorioVacuna(Vacunatorio vacunatorio) {
		List<VacunaStockDTO> listaStock = new ArrayList<>();
		Set<VacunatorioVacuna> listaVacunatorioVacuna = vacunatorio.getVacunas();
		for (VacunatorioVacuna vacunatorioVacuna : listaVacunatorioVacuna) {
			listaStock.add(new VacunaStockDTO(vacunatorioVacuna.getVacuna().getId(), vacunatorioVacuna.getVacuna().getNombre(), vacunatorioVacuna.getFecha(), vacunatorioVacuna.getStock()));
		}
		VacunatorioVacunaDTO vacunatorioVacunaDTO = new VacunatorioVacunaDTO(vacunatorio.getId(), vacunatorio.getNombre(), listaStock);
		return vacunatorioVacunaDTO;
	}

	
}
