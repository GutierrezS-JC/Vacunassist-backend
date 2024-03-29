package com.JDR.Vacunassist.Repository;

import java.time.LocalDate;
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
	
	@Query(value="SELECT MAX(time(fecha_aplicacion)) as ultimo_turno FROM turno where date(fecha_aplicacion) = :fecha and vacunatorio_id = :vacunatorioId",
			nativeQuery = true)
	String getUltimoTurnoEnFechaString(String fecha, Integer vacunatorioId);

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
			+ "vacuna.nombre as vacuna_nombre, vacuna.id as vacuna_id\r\n"
			+ "FROM turno t \r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "LEFT JOIN vacuna ON (vacuna.id = t.vacuna_id)\r\n"
			+ "WHERE t.paciente_id = :pacienteId\r\n"
			+ "ORDER BY date(fecha_aplicacion) DESC",
			nativeQuery = true)
	List<Object[]> getTurnosPaciente(Integer pacienteId);

	@Query(value="SELECT vacuna.id as vacuna_id, vacuna.nombre as vacuna_nombre,\r\n"
			+ "t.fecha_aplicacion as fecha_aplicacion,\r\n"
			+ "t.fecha_aplicacion as fecha_detalle, z.id as zona_id, z.nombre_zona as zona_nombre,\r\n"
			+ "vac.id as vacunatorio_id, vac.nombre as nombre_vacunatorio,\r\n"
			+ "vacuna.id as vacuna_id_detalle, vacuna.nombre as nombre_vacuna_detalle\r\n"
			+ "FROM vacuna \r\n"
			+ "LEFT JOIN turno t ON (t.vacuna_id = vacuna.id)\r\n"
			+ "LEFT JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE paciente_id = :pacienteId\r\n"
			+ "GROUP BY vacuna_id, vacuna.nombre, fecha_detalle, z.id, z.nombre_zona, vac.id, vac.nombre,\r\n"
			+ "vacuna.id, vacuna.nombre\r\n"
			+ "ORDER BY vacuna_id, fecha_aplicacion DESC, fecha_detalle",
			nativeQuery = true)
	List<Object[]> getVacunasPaciente(Integer pacienteId);

	@Query(value="SELECT vacuna.id as vacuna_id, vacuna.nombre as vacuna_nombre,\r\n"
			+ "CASE t.vacuna_id\r\n"
			+ "  WHEN 1 THEN 'Covid'\r\n"
			+ "  WHEN 2 THEN 'Covid'\r\n"
			+ "  WHEN 3 THEN 'Covid'\r\n"
			+ "  WHEN 4 THEN 'Gripe'\r\n"
			+ "  WHEN 5 THEN 'Amarilla'\r\n"
			+ "END AS tipo_vacuna,\r\n"
			+ "t.fecha_aplicacion as fecha_aplicacion,\r\n"
			+ "t.fecha_aplicacion as fecha_detalle, z.id as zona_id, z.nombre_zona as zona_nombre,\r\n"
			+ "vac.id as vacunatorio_id, vac.nombre as nombre_vacunatorio,\r\n"
			+ "vacuna.id as vacuna_id_detalle, vacuna.nombre as nombre_vacuna_detalle\r\n"
			+ "FROM vacuna \r\n"
			+ "LEFT JOIN turno t ON (t.vacuna_id = vacuna.id)\r\n"
			+ "LEFT JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE paciente_id = :pacienteId\r\n"
			+ "ORDER BY vacuna_id, fecha_aplicacion DESC, fecha_detalle",
			nativeQuery = true)
	List<Object[]> getVacunasPacienteV2(Integer pacienteId);
	
	@Query(value="SELECT vacuna.id as vacuna_id,\r\n"
			+ "CASE t.vacuna_id\r\n"
			+ "  WHEN 1 THEN 'Covid'\r\n"
			+ "  WHEN 2 THEN 'Covid'\r\n"
			+ "  WHEN 3 THEN 'Covid'\r\n"
			+ "  WHEN 4 THEN 'Gripe'\r\n"
			+ "  WHEN 5 THEN 'Amarilla'\r\n"
			+ "END AS tipo_vacuna,\r\n"
			+ "t.fecha_aplicacion as fecha_aplicacion\r\n"
			+ "FROM vacuna \r\n"
			+ "LEFT JOIN turno t ON (t.vacuna_id = vacuna.id)\r\n"
			+ "LEFT JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE paciente_id = 10\r\n"
			+ "ORDER BY vacuna_id, fecha_aplicacion DESC",
			nativeQuery = true)
	List<Object[]> getVacunasPacienteV3(Integer pacienteId);

	@Query(value="SELECT\r\n"
			+ "CASE t.vacuna_id\r\n"
			+ "  WHEN 1 THEN 'Covid'\r\n"
			+ "  WHEN 2 THEN 'Covid'\r\n"
			+ "  WHEN 3 THEN 'Covid'\r\n"
			+ "  WHEN 4 THEN 'Gripe'\r\n"
			+ "  WHEN 5 THEN 'Amarilla'\r\n"
			+ "END AS tipo_vacuna,\r\n"
			+ "t.fecha_aplicacion as fecha_detalle, z.id as zona_id, z.nombre_zona as zona_nombre,\r\n"
			+ "vac.id as vacunatorio_id, vac.nombre as nombre_vacunatorio,\r\n"
			+ "vacuna.id as vacuna_id_detalle, vacuna.nombre as nombre_vacuna_detalle\r\n"
			+ "FROM vacuna \r\n"
			+ "LEFT JOIN turno t ON (t.vacuna_id = vacuna.id)\r\n"
			+ "LEFT JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE paciente_id = 10\r\n"
			+ "ORDER BY vacuna_id, fecha_aplicacion DESC, fecha_detalle",
			nativeQuery = true)
	List<Object[]> getDetallesPacienteV3(Integer pacienteId);
	
	@Query(value="SELECT vacuna.id as vacuna_id,\r\n"
			+ "CASE t.vacuna_id\r\n"
			+ "  WHEN 1 THEN 'Covid'\r\n"
			+ "  WHEN 2 THEN 'Covid'\r\n"
			+ "  WHEN 3 THEN 'Covid'\r\n"
			+ "  WHEN 4 THEN 'Gripe'\r\n"
			+ "  WHEN 5 THEN 'Amarilla'\r\n"
			+ "END AS tipo_vacuna,\r\n"
			+ "t.fecha_aplicacion as fecha_aplicacion,\r\n"
			+ "t.fecha_aplicacion as fecha_detalle, z.id as zona_id, z.nombre_zona as zona_nombre,\r\n"
			+ "vac.id as vacunatorio_id, vac.nombre as nombre_vacunatorio,\r\n"
			+ "vacuna.id as vacuna_id_detalle, vacuna.nombre as nombre_vacuna_detalle\r\n"
			+ "FROM vacuna \r\n"
			+ "LEFT JOIN turno t ON (t.vacuna_id = vacuna.id)\r\n"
			+ "LEFT JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE paciente_id = :pacienteId and t.asistio = true and vacuna.id IN (1,2,3)\r\n"
			+ "ORDER BY vacuna_id, fecha_aplicacion DESC, fecha_detalle",
			nativeQuery = true)
	List<Object[]> getVacunasCovidPaciente(Integer pacienteId);
	
	@Query(value="SELECT vacuna.id as vacuna_id,\r\n"
			+ "CASE t.vacuna_id\r\n"
			+ "  WHEN 1 THEN 'Covid'\r\n"
			+ "  WHEN 2 THEN 'Covid'\r\n"
			+ "  WHEN 3 THEN 'Covid'\r\n"
			+ "  WHEN 4 THEN 'Gripe'\r\n"
			+ "  WHEN 5 THEN 'Amarilla'\r\n"
			+ "END AS tipo_vacuna,\r\n"
			+ "t.fecha_aplicacion as fecha_aplicacion,\r\n"
			+ "t.fecha_aplicacion as fecha_detalle, z.id as zona_id, z.nombre_zona as zona_nombre,\r\n"
			+ "vac.id as vacunatorio_id, vac.nombre as nombre_vacunatorio,\r\n"
			+ "vacuna.id as vacuna_id_detalle, vacuna.nombre as nombre_vacuna_detalle\r\n"
			+ "FROM vacuna \r\n"
			+ "LEFT JOIN turno t ON (t.vacuna_id = vacuna.id)\r\n"
			+ "LEFT JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE paciente_id = :pacienteId and t.asistio = true and vacuna.id IN (4)\r\n"
			+ "ORDER BY vacuna_id, fecha_aplicacion DESC, fecha_detalle",
			nativeQuery = true)
	List<Object[]> getVacunasGripePaciente(Integer pacienteId);
	
	@Query(value="SELECT vacuna.id as vacuna_id,\r\n"
			+ "CASE t.vacuna_id\r\n"
			+ "  WHEN 1 THEN 'Covid'\r\n"
			+ "  WHEN 2 THEN 'Covid'\r\n"
			+ "  WHEN 3 THEN 'Covid'\r\n"
			+ "  WHEN 4 THEN 'Gripe'\r\n"
			+ "  WHEN 5 THEN 'Amarilla'\r\n"
			+ "END AS tipo_vacuna,\r\n"
			+ "t.fecha_aplicacion as fecha_aplicacion,\r\n"
			+ "t.fecha_aplicacion as fecha_detalle, z.id as zona_id, z.nombre_zona as zona_nombre,\r\n"
			+ "vac.id as vacunatorio_id, vac.nombre as nombre_vacunatorio,\r\n"
			+ "vacuna.id as vacuna_id_detalle, vacuna.nombre as nombre_vacuna_detalle\r\n"
			+ "FROM vacuna \r\n"
			+ "LEFT JOIN turno t ON (t.vacuna_id = vacuna.id)\r\n"
			+ "LEFT JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "LEFT JOIN vacunatorio vac ON (vac.id = t.vacunatorio_id)\r\n"
			+ "LEFT JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE paciente_id = :pacienteId and t.asistio = true and vacuna.id IN (5)\r\n"
			+ "ORDER BY vacuna_id, fecha_aplicacion DESC, fecha_detalle",
			nativeQuery = true)
	List<Object[]> getVacunasFiebreAmarillaPaciente(Integer pacienteId);

	@Query(value="SELECT id, CASE id WHEN 5 THEN 'Fiebre Amarilla' END AS tipo_vacuna FROM vacuna WHERE id = 5", nativeQuery = true)
	Object[] getFiebreAmarillaDummmy();

	@Query(value="SELECT id, asistio, time_format(time(fecha_aplicacion),'%H:%i'), fecha_asignacion, paciente_id,\r\n"
			+ "vacuna_id, vacunatorio_id from turno where time_format(time(fecha_aplicacion),'%H:%i') = :hora and date(fecha_aplicacion) = :fecha", nativeQuery = true)
	Object[] buscarTurnoExiste(String fecha, String hora);
	
	@Query(value="SELECT id, asistio, time_format(time(fecha_aplicacion),'%H:%i'), fecha_asignacion, paciente_id,\r\n"
			+ "vacuna_id, vacunatorio_id from turno where time_format(time(fecha_aplicacion),'%H:%i') = :hora\r\n"
			+ "and date(fecha_aplicacion) = :fecha and vacunatorio_id = :vacunatorioId", nativeQuery = true)
	Object[] buscarTurnoExisteEnVacun(String fecha, String hora, Integer vacunatorioId);
}
