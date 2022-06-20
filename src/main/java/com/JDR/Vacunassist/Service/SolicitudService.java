package com.JDR.Vacunassist.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Dto.SolicitudDTO;
import com.JDR.Vacunassist.Repository.SolicitudRepository;

@Service
public class SolicitudService {

	@Autowired
	SolicitudRepository solicitudRepository;

	public List<SolicitudDTO> getSolicitudes() {
		// TODO Auto-generated method stub
		return null;
	}
}
