package com.JDR.Vacunassist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer>{

}
