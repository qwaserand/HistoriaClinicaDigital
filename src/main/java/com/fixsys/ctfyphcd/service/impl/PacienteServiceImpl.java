package com.fixsys.ctfyphcd.service.impl;

import com.fixsys.ctfyphcd.exceptions.custom.BadRequestException;
import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Role;
import com.fixsys.ctfyphcd.repository.PacienteRepository;
import com.fixsys.ctfyphcd.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    /*----------------------------------------------------------------------------*/

    @Override
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("id invalido"));
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        paciente.getRoles().add(Role.PACIENTE);
        return pacienteRepository.save(paciente);
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public Paciente buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new BadRequestException("Paciente no encontrado con DNI: " + dni));
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public void deleteAllPacientes() {
        pacienteRepository.deleteAll();
        pacienteRepository.resetPacienteSequence(); // Reinicia el índice
    }

    /*----------------------------------------------------------------------------*/

}
