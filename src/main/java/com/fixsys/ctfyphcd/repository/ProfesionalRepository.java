package com.fixsys.ctfyphcd.repository;

import com.fixsys.ctfyphcd.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    Profesional findByDni(String dni);
    boolean existsByDni(String dni);
}
