package com.fixsys.ctfyphcd.controller;

import com.fixsys.ctfyphcd.dto.CrearTurnoRequest;
import com.fixsys.ctfyphcd.dto.CrearTurnoResponse;
import com.fixsys.ctfyphcd.dto.ReprogramarTurnoRequest;
import com.fixsys.ctfyphcd.dto.ReprogramarTurnoResponse;
import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Turno;
import com.fixsys.ctfyphcd.service.PacienteService;
import com.fixsys.ctfyphcd.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/paciente")
    public List<Paciente>  listPacientes() {
        return pacienteService.findAll();
    }

    @PostMapping("/paciente")
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente nuevoPaciente) {
        Paciente pacienteRegistrado = pacienteService.registrarPaciente(nuevoPaciente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/turnos/{dni}")
    public ResponseEntity<List<Turno>> obtenerTurnosPorDni(@PathVariable String dni) {
        List<Turno> turnos = turnoService.obtenerTurnosPorDni(dni);
        if (turnos != null) {
            return new ResponseEntity<>(turnos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/turno/nuevo")
    public ResponseEntity<CrearTurnoResponse> crearNuevoTurno(@RequestParam String dni,
                                                              @RequestBody CrearTurnoRequest nuevoTurnoRequest,
                                                              @RequestParam String estado) {
        CrearTurnoResponse turnoCreado = turnoService.crearNuevoTurno(
                dni,
                nuevoTurnoRequest.getFechaTurno(),
                nuevoTurnoRequest.getHorario(),
                estado
        );
        if(turnoCreado != null) {
            return new ResponseEntity<>(turnoCreado, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/turno/reprogramar/{id}")
    public ResponseEntity<ReprogramarTurnoResponse> reprogramarTurno(@PathVariable Long id,
                                                  @RequestParam String dni,
                                                  @RequestBody ReprogramarTurnoRequest request,
                                                                     @RequestParam String estado) {
        ReprogramarTurnoResponse turnoReprogramado = turnoService.reprogramarTurno(
                id,
                dni,
                request.getFechaTurno(),
                request.getHorario(),
                estado);
        if(turnoReprogramado != null) {
            return new ResponseEntity<>(turnoReprogramado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/turno/cancelar/{id}")
    public ResponseEntity<Turno> cancelarTurno(@PathVariable Long id) {
        Turno turnoCancelado = turnoService.cancelarTurno(id);
        if(turnoCancelado != null) {
            return new ResponseEntity<>(turnoCancelado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
