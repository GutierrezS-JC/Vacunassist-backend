package com.JDR.Vacunassist.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.VacunaPost;
import com.JDR.Vacunassist.Model.Vacuna;
import com.JDR.Vacunassist.Repository.VacunaRepository;

@Service
public class VacunaService {

	@Autowired
	VacunaRepository vacunaRepository;

	public List<VacunaPost> devolverVacunas() {
		List<Vacuna> listaVacunas = vacunaRepository.findAll();
		List<VacunaPost> listaResponse = this.convertirVacunas(listaVacunas);
		return listaResponse;
	}

	private List<VacunaPost> convertirVacunas(List<Vacuna> listaVacunas) {
		return listaVacunas.stream().map(vacuna -> mapearVacuna(vacuna)).collect(Collectors.toList());
	}

	private VacunaPost mapearVacuna(Vacuna vacuna) {
		VacunaPost vacunaPost = new VacunaPost(vacuna.getId(),vacuna.getNombre(), vacuna.getNum_dosis(), vacuna.getFechaVencimiento());
		return vacunaPost;
	}

	public VacunaPost cargarVacuna(VacunaPost vacunaPost) {
		Vacuna vacunaExiste = vacunaRepository.findByNombre(vacunaPost.getNombre()).orElse(null);
		if(vacunaExiste == null) {
			Vacuna vacunaNueva = new Vacuna(vacunaPost.getId(), vacunaPost.getNombre(), vacunaPost.getNum_dosis(), vacunaPost.getFechaVencimiento());
			Vacuna vacunaRef = vacunaRepository.save(vacunaNueva);
			
			VacunaPost response = new VacunaPost(vacunaRef.getId(), vacunaRef.getNombre(), vacunaRef.getNum_dosis(), vacunaRef.getFechaVencimiento());
			return response;
		}
		else{
			return null;
		}
	}
}
