package com.fixsys.ctfyphcd.controller;

import com.fixsys.ctfyphcd.model.LoginRequest;
import com.fixsys.ctfyphcd.model.Profesional;
import com.fixsys.ctfyphcd.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registerProfesional")
    public Profesional registerProfesional(@RequestBody Profesional profesional) {
        return authService.registerProfesional(profesional);
    }

    @PostMapping("/loginProfesional")
    public Profesional loginProfesional(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateProfesional(loginRequest.getDni(), loginRequest.getPassword());
    }
}
