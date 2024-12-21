package com.fixsys.ctfyphcd.service.impl;

import com.fixsys.ctfyphcd.model.Profesional;
import com.fixsys.ctfyphcd.model.Role;
import com.fixsys.ctfyphcd.repository.ProfesionalRepository;
import com.fixsys.ctfyphcd.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //------------------------------------------------------------------------------

    @Override
    public List<Profesional> getAllProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public Profesional getProfesionalById(Long id) {
        return profesionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesional not found with id: " + id));
    }

    @Override
    public Profesional registerProfesional(Profesional profesional) {
        // Encripta la contraseña antes de guardar
        String encodedPassword = passwordEncoder.encode(profesional.getPassword());
        profesional.setPassword(encodedPassword);
        if (profesional.getRoles() == null || profesional.getRoles().isEmpty()) {
            profesional.setRoles(Set.of(Role.PROFESIONAL));
        }
        return profesionalRepository.save(profesional);
    }

    @Override
    public void deleteProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }
}
