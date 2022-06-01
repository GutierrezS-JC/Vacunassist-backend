package com.JDR.Vacunassist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Vacuna;
import com.JDR.Vacunassist.Model.Vacunatorio;
import com.JDR.Vacunassist.Model.VacunatorioVacuna;

@Repository
public interface VacunatorioVacunaRepository extends JpaRepository<VacunatorioVacuna, Integer>{
	VacunatorioVacuna findByVacunatorioAndVacuna(Vacunatorio vacunatorioBuscado, Vacuna vacunaBuscada);
}
