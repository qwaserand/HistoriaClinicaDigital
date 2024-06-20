package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.model.Profesional;
import com.fixsys.ctfyphcd.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Profesional profesional = profesionalRepository.findByDni(dni);
        if (profesional == null) {
            throw new UsernameNotFoundException("Profesional not found");
        }
        return new org.springframework.security.core.userdetails.User(
                profesional.getDni(),
                profesional.getPassword(),
                new ArrayList<>());
    }
}
