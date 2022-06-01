package com.JDR.Vacunassist.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Vacunador;

@Repository
public interface VacunadorRepository extends JpaRepository<Vacunador, Integer>{

	Vacunador findByDni(Integer dni);
	
	List<Vacunador>findByDniGreaterThanAndDniLessThan(Integer dniStart, Integer dniEnd);

//	Vacunador findByVacunadorAndPassword(Vacunador vacunadorBuscado, String password);

	Vacunador findByEmailAndPassword(String email, String password);
}
