package com.JDR.Vacunassist.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.PermisoDTO;
import com.JDR.Vacunassist.Dto.RolDTO;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunatorioDTO;
import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Model.Permiso;
import com.JDR.Vacunassist.Model.Vacunador;
import com.JDR.Vacunassist.Model.VacunadorZona;
import com.JDR.Vacunassist.Model.Zona;
import com.JDR.Vacunassist.Repository.VacunadorRepository;

@Service
public class VacunadorService {

	@Autowired
	VacunadorRepository vacunadorRepository;
	
	public List<VacunadorDTO> devolverVacunadores() {
		List<Vacunador> vacunadorList = vacunadorRepository.findAll();
		List<VacunadorDTO> response = this.convertirVacunador(vacunadorList);
		return response;	
	}

	private List<VacunadorDTO> convertirVacunador(List<Vacunador> vacunadorList) {
		return vacunadorList.stream().map(vacunador -> mapearVacunador(vacunador)).collect(Collectors.toList());
	}

	private VacunadorDTO mapearVacunador(Vacunador vacunador) {
		VacunadorDTO vacunadorDTO = new VacunadorDTO();
		List<PermisoDTO> listaPermisosDTO = new ArrayList<>();
		List<ZonaDTO> listaZonasDTO = new ArrayList<>();
		
		for(Permiso permiso : vacunador.getRol().getPermisos()) {
			PermisoDTO permisoNuevo = new PermisoDTO(permiso.getId(), permiso.getNombrePermiso());
			listaPermisosDTO.add(permisoNuevo);
		}
		
		for(VacunadorZona vacunadorZona : vacunador.getZonas()) {
			VacunatorioDTO vacunatorioDTO = new VacunatorioDTO(vacunadorZona.getZona().getVacunatorio().getId(), vacunadorZona.getZona().getVacunatorio().getNombre());
			ZonaDTO zonaNueva = new ZonaDTO(vacunadorZona.getZona().getId(), vacunadorZona.getZona().getNombreZona(), vacunatorioDTO);
			listaZonasDTO.add(zonaNueva);
		}
		
		vacunadorDTO.setId(vacunador.getId());
		vacunadorDTO.setDni(vacunador.getDni());
		vacunadorDTO.setEmail(vacunador.getEmail());
		vacunadorDTO.setNombre(vacunador.getNombre());
		vacunadorDTO.setApellido(vacunador.getApellido());
		vacunadorDTO.setFechaNacimiento(vacunador.getFechaNacimiento());
		vacunadorDTO.setRol(new RolDTO(vacunador.getRol().getId(), vacunador.getRol().getNombreRol(), listaPermisosDTO));
		vacunadorDTO.setZonas(listaZonasDTO);
		
		return vacunadorDTO;
	}

}
