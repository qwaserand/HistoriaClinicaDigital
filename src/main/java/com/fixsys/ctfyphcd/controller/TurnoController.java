//package com.fixsys.ctfyphcd.controller;
//
//import com.fixsys.ctfyphcd.model.Turno;
//import com.fixsys.ctfyphcd.service.TurnoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/turno")
//public class TurnoController {
//
//    @Autowired
//    private TurnoService turnoService;
//
//    @GetMapping("/{dni}")
//    public List<Turno> obtenerTurnosPorDni(@PathVariable String dni) {
//        return turnoService.obtenerTurnosPorDni(dni);
//    }
//
//    @PostMapping("/nuevo")
//    public Turno crearNuevoTurno(@RequestBody Turno turno) {
//        return turnoService.crearNuevoTurno(turno);
//    }
//
//    @PutMapping("/reprogramar/{id}")
//    public Turno reprogramarTurno(@PathVariable Long id, @RequestBody LocalDateTime nuevaFecha){
//        return turnoService.reprogramarTurno(id, nuevaFecha);
//    }
//
//    @DeleteMapping("/cancelar/{id}")
//    public ResponseEntity<Void> cancelarTurno(@PathVariable Long id) {
//        turnoService.cancelarTurno(id);
//        return ResponseEntity.ok().build();
//    }
//
//}
