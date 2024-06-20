package com.fixsys.ctfyphcd.repository;

import com.fixsys.ctfyphcd.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByDni(String dni);
    boolean existsByDni(String dni);
}
