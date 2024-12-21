package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Profesional;
import com.fixsys.ctfyphcd.model.Role;
import com.fixsys.ctfyphcd.repository.PacienteRepository;
import com.fixsys.ctfyphcd.repository.ProfesionalRepository;
import com.fixsys.ctfyphcd.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    //------------------------------------------------------------------------------

    private static final String ADMIN_DNI = "admin_dni";
    private static final String ADMIN_PASSWORD = "admin_password";

    public String authenticateProfesional(String dni, String password) {
        Profesional profesional = profesionalRepository.findByDni(dni);
        if (profesional == null || !passwordEncoder.matches(password, profesional.getPassword())){
            throw new RuntimeException("Invalid dni or password");
        }
        return jwtUtil.generateToken(dni, profesional.getRoles());
    }

    public String authenticateAdmin(String dni, String password) {
        if (ADMIN_DNI.equals(dni) && ADMIN_PASSWORD.equals(password)) {
            Set<Role> adminRoles = Set.of(Role.ADMIN);
            return jwtUtil.generateToken(dni, adminRoles);
        }
        throw new RuntimeException("Invalid admin credentials");
    }
}
