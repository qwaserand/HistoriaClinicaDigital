package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.model.Profesional;
import com.fixsys.ctfyphcd.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Profesional registerProfesional(Profesional profesional) {
        String encodedPassword = passwordEncoder.encode(profesional.getPassword());
        profesional.setPassword(encodedPassword);
        return profesionalRepository.save(profesional);
    }

    public Profesional authenticateProfesional(String dni, String password) {
        Profesional profesional = profesionalRepository.findByDni(dni);
        if (profesional == null || !passwordEncoder.matches(password, profesional.getPassword())){
            throw new RuntimeException("Invalid dni or password");
        }
        return profesional;
    }
}
