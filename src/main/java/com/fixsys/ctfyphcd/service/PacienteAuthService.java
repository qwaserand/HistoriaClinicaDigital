package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Role;
import com.fixsys.ctfyphcd.repository.PacienteRepository;
import com.fixsys.ctfyphcd.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PacienteAuthService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticatePaciente(String dni) {
        Paciente paciente = pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Genera el token con el rol PACIENTE
        Set<Role> roles = Set.of(Role.PACIENTE);
        return jwtUtil.generateToken(paciente.getDni(), roles);
    }
}
