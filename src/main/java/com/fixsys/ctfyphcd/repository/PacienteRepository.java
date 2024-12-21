package com.fixsys.ctfyphcd.repository;

import com.fixsys.ctfyphcd.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findById(Long id);
    Optional<Paciente> findByDni(String dni);
    boolean existsByDni(String dni);
    @Modifying
    @Query(value = "ALTER SEQUENCE paciente_id_seq RESTART WITH 1", nativeQuery = true)
    void resetPacienteSequence();

}
