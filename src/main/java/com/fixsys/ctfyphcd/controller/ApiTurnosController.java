package com.fixsys.ctfyphcd.controller;

import com.fixsys.ctfyphcd.dto.request.TurnoRequestDTO;
import com.fixsys.ctfyphcd.model.Turno;
import com.fixsys.ctfyphcd.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiTurnosController {

    @Autowired
    private TurnoService turnoService;

    /*----------------------------------------------------------------------------*/

    @GetMapping
    public ResponseEntity<List<Turno>> listAllTurnos() {
        List<Turno> turnos = turnoService.obtenerTurnos();
        if (turnos != null) {
            return new ResponseEntity<>(turnos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUnTurno(@PathVariable Long id){
        Turno turno = turnoService.obtenerUnTurno(id);
        return ResponseEntity.ok(turno);
    }

    @PostMapping
    public ResponseEntity<?> crearNuevoTurno(@RequestBody TurnoRequestDTO
                                                         turnoRequestDTO) {

        Turno turno = turnoService.crearNuevoTurno(turnoRequestDTO);
        return new ResponseEntity<>(turno, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> reprogramarTurno(@PathVariable Long id,
                                              @RequestBody TurnoRequestDTO turnoRequestDTO) {

        Turno turnoReprogramado = turnoService.reprogramarTurno(turnoRequestDTO, id);
        return  new ResponseEntity<>(turnoReprogramado, HttpStatus.OK);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarTurno(@PathVariable Long id) {
        String resultado = turnoService.cancelarTurno(id);
        if("Turno cancelado exitosamente.".equals(resultado)) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}
