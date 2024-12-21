package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.model.Paciente;

import java.util.List;

public interface PacienteService {

    Paciente findById(Long id);
    Paciente registrarPaciente(Paciente paciente);
    Paciente buscarPorDni(String dni);
    List<Paciente> findAll();
    void deletePaciente(Long id);
    void deleteAllPacientes();
}
