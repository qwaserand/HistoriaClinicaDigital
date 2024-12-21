package com.fixsys.ctfyphcd.controller;

import com.fixsys.ctfyphcd.dto.request.TurnoRequestDTO;
import com.fixsys.ctfyphcd.model.Turno;
import com.fixsys.ctfyphcd.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/turnos")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiTurnosController {

    @Autowired
    private TurnoService turnoService;

    /*----------------------------------------------------------------------------*/

    @GetMapping
    @PreAuthorize("hasAnyRole('PACIENTE', 'PROFESIONAL', 'ADMIN')")
    public ResponseEntity<List<Turno>> listAllTurnos() {
        List<Turno> turnos = turnoService.obtenerTurnos();
        if (turnos != null) {
            return new ResponseEntity<>(turnos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*----------------------------------------------------------------------------*/

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUnTurno(@PathVariable Long id){
        Turno turno = turnoService.obtenerUnTurno(id);
        return ResponseEntity.ok(turno);
    }

    /*----------------------------------------------------------------------------*/

    @GetMapping("/mis-turnos")
    @PreAuthorize("hasRole('PACIENTE')")
    public ResponseEntity<List<Turno>> getMisTurnos(Authentication authentication) {
        // Obtén el DNI del paciente desde el token
        String dni = authentication.getName();

        // Recupera los turnos asociados al paciente
        List<Turno> turnos = turnoService.obtenerTurnosPorDniPaciente(dni);

        // Responde con los turnos o un status NO_CONTENT si no hay turnos
        if (turnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turnos);
    }

    /*----------------------------------------------------------------------------*/

    @PostMapping
    public ResponseEntity<?> crearNuevoTurno(@RequestBody TurnoRequestDTO
                                                         turnoRequestDTO) {

        Turno turno = turnoService.crearNuevoTurno(turnoRequestDTO);
        return new ResponseEntity<>(turno, HttpStatus.CREATED);
    }

    /*----------------------------------------------------------------------------*/

    @PutMapping("/{id}")
    public ResponseEntity<?> reprogramarTurno(@PathVariable Long id,
                                              @RequestBody TurnoRequestDTO turnoRequestDTO) {

        Turno turnoReprogramado = turnoService.reprogramarTurno(turnoRequestDTO, id);
        return  new ResponseEntity<>(turnoReprogramado, HttpStatus.OK);
    }

    /*----------------------------------------------------------------------------*/

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Map<String, String>> cancelarTurno(@PathVariable Long id) {
        String resultado = turnoService.cancelarTurno(id);

        // Devolver la respuesta como un JSON con un "message"
        if ("Turno cancelado exitosamente.".equals(resultado)) {
            return ResponseEntity.ok(Collections.singletonMap("message", resultado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", resultado));
        }
    }

    /*----------------------------------------------------------------------------*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }

    /*----------------------------------------------------------------------------*/

    @PostMapping("/{turnoId}/recetas")
    public ResponseEntity<String> subirReceta(@PathVariable Long turnoId, @RequestParam("archivo") MultipartFile archivo) {
        // Lógica para manejar el archivo (por ejemplo, guardarlo en el sistema de archivos o en la base de datos)
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío");
        }

        // Guardar el archivo asociado al turno (implementa la lógica según tus necesidades)
        String mensaje = turnoService.guardarReceta(turnoId, archivo);

        return ResponseEntity.ok(mensaje);
    }

    /*----------------------------------------------------------------------------*/

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAllTurnos() {
        turnoService.deleteAllTurnos();
        return ResponseEntity.ok("Todos los turnos han sido eliminados.");
    }

    /*----------------------------------------------------------------------------*/
}
