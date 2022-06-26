package com.JDR.Vacunassist.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.TurnoResponse;
import com.JDR.Vacunassist.Repository.TurnoRepository;

@Service
public class TurnoService {

	@Autowired
	TurnoRepository turnoRepository;

	public List<TurnoResponse> getAllTurnos() {
		List<Object[]> listaDb = turnoRepository.getAllTurnos();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}

	public List<TurnoResponse> getTurnosCovid() {
		List<Object[]> listaDb = turnoRepository.getTurnosCovid();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}
	
	public List<TurnoResponse> getTurnosGripe() {
		List<Object[]> listaDb = turnoRepository.getTurnosGripe();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}
	
	public List<TurnoResponse> getTurnosYellow() {
		List<Object[]> listaDb = turnoRepository.getTurnosYellow();
		List<TurnoResponse> listaResponse = this.mapearTurnos(listaDb);
		return listaResponse;
	}

	private List<TurnoResponse> mapearTurnos(List<Object[]> listaDb) {
		List<TurnoResponse> listaResponse = new ArrayList<>();
		for(Object[] data : listaDb) {
			TurnoResponse turno = new TurnoResponse((Integer)data[0],(String)data[1],(String)data[2],(Integer)data[3],
					(String)data[4],(String)data[5],(String)data[6].toString(),(String)data[7].toString(),(Boolean)data[8]);
			listaResponse.add(turno);
		}
		return listaResponse;
	}

}
