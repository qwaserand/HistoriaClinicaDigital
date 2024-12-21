package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.exceptions.custom.BadRequestException;
import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Role;
import com.fixsys.ctfyphcd.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    //------------------------------------------------------------------------------

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("id invalido"));
    }

    public Paciente registrarPaciente(Paciente paciente) {
        paciente.getRoles().add(Role.PACIENTE);
        return pacienteRepository.save(paciente);
    }

    public Paciente buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new BadRequestException("Paciente no encontrado con DNI: " + dni));
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public void DeletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

}
