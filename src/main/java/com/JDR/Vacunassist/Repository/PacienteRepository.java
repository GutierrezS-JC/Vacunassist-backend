package com.JDR.Vacunassist.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Paciente findByDni(Integer dni);

	Paciente findByEmailAndPassword(String email, String password);
	
	Paciente findByEmailAndPasswordAndCodigo(String email, String password, Integer codigo);

	@Query(value="SELECT coalesce(MAX(paciente.codigo),1000) from paciente", nativeQuery = true)
	Integer getUltimoCodigoCreado();
	
	@Query(value="SELECT MAX(time(fecha_aplicacion)) as ultimo_turno FROM turno where date(fecha_aplicacion) = :fecha and vacunatorio_id = :vacunatorioId",
			nativeQuery = true)
	Object getUltimoTurnoEnFecha(String fecha, Integer vacunatorioId);

	//Pacientes por zona
	@Query(value="SELECT p.id, p.dni, p.email, p.nombre, p.apellido, p.es_de_riesgo, p.fecha_nacimiento, p.rol_id, z.nombre_zona, vac.nombre as nombre_vacunatorio\r\n"
			+ "FROM paciente p INNER JOIN zona z ON (p.zona_id = z.id)\r\n"
			+ "INNER JOIN vacunatorio vac ON (vac.zona_id = z.id)\r\n"
			+ "WHERE z.id = :zonaId",
			nativeQuery = true)
	List<Object[]> getPacientesEnZona(Integer zonaId);

	@Query(value="SELECT distinct(p.id), p.nombre, p.apellido, p.codigo, p.dni, p.email,\r\n"
			+ " p.es_de_riesgo, p.fecha_nacimiento, p.password, p.rol_id, p.zona_id from paciente p\r\n"
			+ "INNER JOIN turno t ON (t.paciente_id = p.id)\r\n"
			+ "INNER JOIN vacuna v ON (t.vacuna_id = v.id)\r\n"
			+ "INNER JOIN solicitud s ON (s.paciente_id = p.id)\r\n"
			+ "WHERE (t.vacuna_id = :vacunaId and p.id = :pacienteId) OR (s.paciente_id = :pacienteId)",
			nativeQuery = true)
	Object getAlreadyHasYellow(Integer vacunaId, Integer pacienteId);

	@Query(value="SELECT t.id as turno_id, p.id as paciente_id, t.fecha_aplicacion, t.fecha_asignacion, t.asistio,\r\n"
			+ "vac.nombre as nombre_vacunatorio, vac.calle, vac.altura, \r\n"
			+ "z.nombre_zona,\r\n"
			+ "vacuna.nombre as vacuna_nombre, vac.id as vacuna_id\r\n"
			+ "FROM turno t \r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "LEFT JOIN vacuna ON (vacuna.id = t.vacuna_id)\r\n"
			+ "WHERE t.paciente_id = :pacienteId",
			nativeQuery = true)
	List<Object[]> getTurnosPaciente(Integer pacienteId);

}
