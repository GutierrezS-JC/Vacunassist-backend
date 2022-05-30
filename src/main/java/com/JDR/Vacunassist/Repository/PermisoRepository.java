package com.JDR.Vacunassist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long>{

}
