package com.JDR.Vacunassist.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.VacunatorioDTO;
import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Model.Zona;
import com.JDR.Vacunassist.Repository.ZonaRepository;

@Service
public class ZonaService {

	@Autowired
	ZonaRepository zonaRepository;

	public ZonaDTO cargarZona(ZonaDTO zonaDTO) {
		Zona zonaNueva = new Zona(zonaDTO.getId(), zonaDTO.getNombreZona());
		Zona zonaCreada = zonaRepository.save(zonaNueva);
	
		return new ZonaDTO(zonaCreada.getId(), zonaCreada.getNombreZona());
	}

	public List<ZonaDTO> devolverZonas() {
		List<Zona> listaZonas = zonaRepository.findByOrderByIdAsc();
		List<ZonaDTO> listaResponse = this.convertirZonas(listaZonas);
		return listaResponse;
	}

	private List<ZonaDTO> convertirZonas(List<Zona> listaZonas) {
		return listaZonas.stream().map(zona -> mapearZona(zona)).collect(Collectors.toList());
	}

	private ZonaDTO mapearZona(Zona zona) {
		VacunatorioDTO vacunatorioResponse;
		if(zona.getVacunatorio()==null) {
			vacunatorioResponse = null;
		}
		else {			
			vacunatorioResponse = new VacunatorioDTO(zona.getVacunatorio().getId(), zona.getNombreZona());
		}
		ZonaDTO zonaResponse = new ZonaDTO(zona.getId(),zona.getNombreZona(), vacunatorioResponse);
		return zonaResponse;
	}

	
	
	
}
