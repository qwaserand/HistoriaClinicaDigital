package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.dto.request.TurnoRequestDTO;
import com.fixsys.ctfyphcd.model.Turno;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TurnoService {

    List<Turno> obtenerTurnos();
    Turno obtenerUnTurno(Long turnoId);
    List<Turno> obtenerTurnosPorDniPaciente(String dni);
    Turno crearNuevoTurno(TurnoRequestDTO turnoRequestDTO);
    Turno reprogramarTurno(TurnoRequestDTO turnoRequestDTO, Long id);
    String cancelarTurno(Long turnoId);
    void eliminarTurno(long id);
    String guardarReceta(Long turnoId, MultipartFile archivo);
    void deleteAllTurnos();

}
