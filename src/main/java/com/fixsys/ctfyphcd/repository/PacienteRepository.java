package com.fixsys.ctfyphcd.repository;

import com.fixsys.ctfyphcd.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findById(Long id);
    Paciente findByDni(String dni);
    boolean existsByDni(String dni);
}
