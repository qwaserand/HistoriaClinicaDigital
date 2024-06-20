package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

}
