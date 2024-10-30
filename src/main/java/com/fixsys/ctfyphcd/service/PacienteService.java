package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.exceptions.custom.BadRequestException;
import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new BadRequestException("id invalido"));
    }

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
