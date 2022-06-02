package com.JDR.Vacunassist.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.PermisoDTO;
import com.JDR.Vacunassist.Dto.RolDTO;
import com.JDR.Vacunassist.Dto.VacunadorDTO;
import com.JDR.Vacunassist.Dto.VacunadorRequest;
import com.JDR.Vacunassist.Dto.VacunatorioDTO;
import com.JDR.Vacunassist.Dto.ValidarVacunador;
import com.JDR.Vacunassist.Dto.ZonaDTO;
import com.JDR.Vacunassist.Excepciones.ResourceNotFoundException;
import com.JDR.Vacunassist.Model.Permiso;
import com.JDR.Vacunassist.Model.Rol;
import com.JDR.Vacunassist.Model.Vacunador;
import com.JDR.Vacunassist.Model.VacunadorZona;
import com.JDR.Vacunassist.Model.Zona;
import com.JDR.Vacunassist.Repository.RolRepository;
import com.JDR.Vacunassist.Repository.VacunadorRepository;
import com.JDR.Vacunassist.Repository.ZonaRepository;

@Service
public class VacunadorService {

	@Autowired
	VacunadorRepository vacunadorRepository;
	
	@Autowired
	RolRepository rolRepository;
	
	@Autowired
	ZonaRepository zonaRepository;
	
	public List<VacunadorDTO> devolverVacunadores() {
		List<Vacunador> vacunadorList = vacunadorRepository.findAll();
		List<VacunadorDTO> response = this.convertirVacunador(vacunadorList);
		return response;	
	}

	//Convierte un objeto Vacunador traido con toda la data redundante de la BD a una version cutom por nosotros
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

	public VacunadorDTO devolverVacunadorPorId(Integer id) throws ResourceNotFoundException{
		Vacunador vacunador = vacunadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro un admin para este id: " + id));
		return mapearVacunador(vacunador);
	}

	public VacunadorDTO devolverVacunadorPorDni(Integer dni) {
		Vacunador vacunador = vacunadorRepository.findByDni(dni);
		if(vacunador!= null) {
			return mapearVacunador(vacunador);
		}
		else return null;
	}

	public List<VacunadorDTO> devolverVacunadoresEnRango(Integer inferiorDni, Integer superiorDni) {
		List<Vacunador> vacunadorList = vacunadorRepository.findByDniGreaterThanAndDniLessThan(inferiorDni, superiorDni);
		List<VacunadorDTO> response = this.convertirVacunador(vacunadorList);
		return response;
	}

	public Boolean devolverSiExisteDniEnVacunadorTable(Integer dni) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByDni(dni);
		if(vacunadorBuscado !=null) return true; else return false;
	}

	public VacunadorDTO cargarVacunador(VacunadorRequest vacunadorReq) {
		Rol rolBuscado = rolRepository.findById(vacunadorReq.getRolId()).get();
		Zona zona = zonaRepository.findById(vacunadorReq.getZonaId()).get();
		
		Vacunador nuevoVacunador = new Vacunador(vacunadorReq.getId(), vacunadorReq.getDni(), vacunadorReq.getEmail(), vacunadorReq.getPassword(),
				vacunadorReq.getClave(), vacunadorReq.getNombre(), vacunadorReq.getApellido(), vacunadorReq.getFechaNacimiento(), rolBuscado);
	
		Vacunador vacunadorCreado = vacunadorRepository.saveAndFlush(nuevoVacunador);

		//Creo el objeto VacunadorZona que representa la union entre ambas entidades participantes
		VacunadorZona vacunadorZona = new VacunadorZona(vacunadorCreado, zona);
		vacunadorCreado.getZonas().add(vacunadorZona);
		vacunadorRepository.saveAndFlush(vacunadorCreado);
		VacunadorDTO vacunadorResponse = this.mapearVacunador(vacunadorCreado);
		
		return vacunadorResponse;
	}

	public VacunadorDTO validarVacunador(ValidarVacunador validarVacunador) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByEmailAndPassword(validarVacunador.getEmail(), validarVacunador.getPassword());
		if(vacunadorBuscado != null) {
			VacunadorDTO vacunadorDTO = this.mapearVacunador(vacunadorBuscado);
			return vacunadorDTO;
		}
		else {			
			return null;
		}
	}

	public Boolean validarVacunadorBoolean(ValidarVacunador validarVacunador) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByEmailAndPassword(validarVacunador.getEmail(), validarVacunador.getPassword());
		if(vacunadorBuscado != null) {
			return true;
		}
		else {			
			return false;
		}
	}

	public VacunadorDTO validarVacunadorConCodigo(ValidarVacunador validarVacunador) {
		Vacunador vacunadorBuscado = vacunadorRepository.findByEmailAndPasswordAndCodigo(validarVacunador.getEmail(), validarVacunador.getPassword(), validarVacunador.getCodigo());
		if(vacunadorBuscado != null) {
			VacunadorDTO vacunadorDTO = this.mapearVacunador(vacunadorBuscado);
			return vacunadorDTO;
		}
		else {			
			return null;
		}
	}

//	public List<VacunadorDTO> devolverVacunadoresEnZona(Integer zonaId) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
