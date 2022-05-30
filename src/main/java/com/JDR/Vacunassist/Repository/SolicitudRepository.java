package com.JDR.Vacunassist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JDR.Vacunassist.Model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

}
