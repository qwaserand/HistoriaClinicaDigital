package com.fixsys.ctfyphcd.controller;

import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.service.PacienteService;
import com.fixsys.ctfyphcd.util.HistoriasClinicas;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class ApiPacientesController {

    @Autowired
    private PacienteService pacienteService;

    /*----------------------------------------------------------------------------*/

    @GetMapping
    public ResponseEntity<List<Paciente>> listPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes != null) {
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente nuevoPaciente) {
        Paciente pacienteRegistrado = pacienteService.registrarPaciente(nuevoPaciente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*----------------------------------------------------------------------------*/

    @PostMapping("/{id}/generarHistoriaClinica")
    public ModelAndView generarHistoriaClinica(@PathVariable("id") Long id,
                                               @RequestParam("anotaciones") String anotaciones,
                                               HttpServletResponse response) {
        Paciente paciente = pacienteService.findById(id);
        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado");
        }

        ModelAndView modelAndView = new ModelAndView(new HistoriasClinicas());
        modelAndView.addObject("paciente", paciente);
        modelAndView.addObject("anotaciones", anotaciones);

        return modelAndView;
    }
}
