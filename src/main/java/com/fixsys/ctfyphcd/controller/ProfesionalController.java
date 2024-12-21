package com.fixsys.ctfyphcd.controller;

import com.fixsys.ctfyphcd.model.Profesional;
import com.fixsys.ctfyphcd.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    /*----------------------------------------------------------------------------*/

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Profesional>> getAllProfesionales() {
        List<Profesional> profesionales = profesionalService.getAllProfesionales();
        return ResponseEntity.ok(profesionales); // Retorna la lista obtenida del servicio
    }

    /*----------------------------------------------------------------------------*/

    @GetMapping("/{id}")
    public Profesional getProfesionalById(@PathVariable Long id) {
        return profesionalService.getProfesionalById(id);
    }

    /*----------------------------------------------------------------------------*/

    @PostMapping
    public Profesional registerProfesional(@RequestBody Profesional profesional) {
        return profesionalService.registerProfesional(profesional);
    }

    /*----------------------------------------------------------------------------*/

    @DeleteMapping("/{id}")
    public void deleteProfesional(@PathVariable Long id) {
        profesionalService.deleteProfesional(id);
    }

    /*----------------------------------------------------------------------------*/

}
