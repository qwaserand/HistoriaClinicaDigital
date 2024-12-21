package com.fixsys.ctfyphcd.controller;

import com.fixsys.ctfyphcd.dto.request.ProfesionalCredentials;
import com.fixsys.ctfyphcd.model.AdminCredentials;
import com.fixsys.ctfyphcd.model.Profesional;
import com.fixsys.ctfyphcd.service.AuthService;
import com.fixsys.ctfyphcd.service.PacienteAuthService;
import com.fixsys.ctfyphcd.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ProfesionalService profesionalService;

    @Autowired
    private PacienteAuthService pacienteAuthService;

    /*----------------------------------------------------------------------------*/

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminCredentials credentials) {
        String token = authService.authenticateAdmin(credentials.getDni(), credentials.getPassword());
        return ResponseEntity.ok().body("{ \"token\": \"" + token + "\" }");
    }

    /*----------------------------------------------------------------------------*/

    @PostMapping("/profesional/login")
    public ResponseEntity<?> loginProfesional(@RequestBody ProfesionalCredentials credentials) {
        String token = authService.authenticateProfesional(credentials.getDni(), credentials.getPassword());
        return ResponseEntity.ok().body("{ \"token\": \"" + token + "\" }");
    }

    /*----------------------------------------------------------------------------*/

    @PostMapping("/registerProfesional")
    public Profesional registerProfesional(@RequestBody Profesional profesional) {
        return profesionalService.registerProfesional(profesional);
    }

    /*----------------------------------------------------------------------------*/

    @PostMapping("/paciente/login")
    public ResponseEntity<?> loginPaciente(@RequestBody Map<String, String> request) {
        String dni = request.get("dni");

        if(dni == null || dni.isEmpty()) {
            return ResponseEntity.badRequest().body("DNI es obligatorio");
        }

        String token = pacienteAuthService.authenticatePaciente(dni);

        return ResponseEntity.ok(Map.of("token", token));
    }

    /*----------------------------------------------------------------------------*/

}
