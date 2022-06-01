package com.JDR.Vacunassist.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.VacunatorioFullDTO;
import com.JDR.Vacunassist.Dto.VacunatorioPost;
import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Model.Vacuna;
import com.JDR.Vacunassist.Model.Vacunatorio;
import com.JDR.Vacunassist.Model.VacunatorioVacuna;
import com.JDR.Vacunassist.Model.Zona;
import com.JDR.Vacunassist.Repository.VacunaRepository;
import com.JDR.Vacunassist.Repository.VacunatorioRepository;
import com.JDR.Vacunassist.Repository.VacunatorioVacunaRepository;
import com.JDR.Vacunassist.Repository.ZonaRepository;

@Service
public class VacunatorioService {
	
	@Autowired
	VacunatorioRepository vacunatorioRepository;
	
	@Autowired
	ZonaRepository zonaRepository;
	
	@Autowired
	VacunaRepository vacunaRepository;
	
	@Autowired
	VacunatorioVacunaRepository vacunatorioVacunaRepository;

	public List<VacunatorioFullDTO> devolverVacunatorios() {
		List<Vacunatorio> vacunatorioList = vacunatorioRepository.findAll();
		List<VacunatorioFullDTO> response = this.convertirVacunatorio(vacunatorioList);
		return response;
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

	public Boolean editarNombreVacunatorio(String nombre, Integer id) {
		Vacunatorio vacunatorioBuscado = vacunatorioRepository.findById(id).get();
		if(vacunatorioBuscado != null) {
			vacunatorioBuscado.setNombre(nombre);
			vacunatorioRepository.save(vacunatorioBuscado);
			return true;
		} else {			
			return false;
		}
	}

	public VacunatorioFullDTO cargarVacunatorio(VacunatorioPost vacunatorioPost) {
		Vacunatorio buscarVacunatorio = vacunatorioRepository.findByNombre(vacunatorioPost.getNombre()).orElse(null);
		if(buscarVacunatorio == null) {
			Zona zonaPost = zonaRepository.findById(vacunatorioPost.getZonaId()).get();
			Vacunatorio nuevoVacunatorio = new Vacunatorio(vacunatorioPost.getId(),vacunatorioPost.getNombre(), vacunatorioPost.getCalle(),
					vacunatorioPost.getAltura(), zonaPost);
			Vacunatorio vacunatorioCreado = vacunatorioRepository.save(nuevoVacunatorio);
			
			//Objeto Creado
			ZonaDTO zonaResponse = new ZonaDTO(vacunatorioCreado.getZona().getId(), vacunatorioCreado.getZona().getNombreZona());
			VacunatorioFullDTO response = new VacunatorioFullDTO(vacunatorioCreado.getId(), vacunatorioCreado.getNombre(),
					vacunatorioCreado.getCalle(), vacunatorioCreado.getAltura(), zonaResponse);
			return response;
		}
		else {			
			return null;
		}
	}

	public Boolean cargarVacuna(Integer vacunatorioId, Integer vacunaId) {
		Vacuna vacunaBuscada = vacunaRepository.findById(vacunaId).get();
		if(vacunaBuscada != null) {
			Vacunatorio vacunatorioBuscado = vacunatorioRepository.findById(vacunatorioId).get();
			VacunatorioVacuna vacunVacuna = new VacunatorioVacuna(vacunatorioBuscado, vacunaBuscada);
			vacunatorioBuscado.getVacunas().add(vacunVacuna);
			vacunatorioRepository.save(vacunatorioBuscado);
			return true;
		}
		else {			
			return false;
		}
	}

	public Integer actualizarStock(Integer vacunatorioId, Integer vacunaId, Integer stock) {
		Vacuna vacunaBuscada = vacunaRepository.findById(vacunaId).get();
		Vacunatorio vacunatorioBuscado = vacunatorioRepository.findById(vacunatorioId).get();
		if(vacunaBuscada != null && vacunatorioBuscado != null) {
			VacunatorioVacuna vacunVacuna = vacunatorioVacunaRepository.findByVacunatorioAndVacuna(vacunatorioBuscado, vacunaBuscada);
			vacunVacuna.setStock(stock);
			vacunatorioVacunaRepository.save(vacunVacuna);
			return vacunVacuna.getStock();
		}
		return null;
	}


}
