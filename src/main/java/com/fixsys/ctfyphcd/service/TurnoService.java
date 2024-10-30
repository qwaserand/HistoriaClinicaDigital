package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.dto.request.TurnoRequestDTO;
import com.fixsys.ctfyphcd.model.Turno;
import java.util.List;

public interface TurnoService {

    public List<Turno> obtenerTurnos();
    public Turno obtenerUnTurno(Long turnoId);
    public Turno crearNuevoTurno(TurnoRequestDTO turnoRequestDTO);
    public Turno reprogramarTurno(TurnoRequestDTO turnoRequestDTO, Long id);
    public String cancelarTurno(Long turnoId);
    public void eliminarTurno(long id);

}
