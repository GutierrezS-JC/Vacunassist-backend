package com.JDR.Vacunassist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.DniValido;

@Repository
public interface DniValidoRepository extends JpaRepository<DniValido, Integer> {

	DniValido findByDni(Integer dni);

}
