package com.JDR.Vacunassist.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Repository.VacunaRepository;

@Service
public class VacunaService {

	@Autowired
	VacunaRepository vacunaRepository;
}
