package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.dto.CrearTurnoResponse;
import com.fixsys.ctfyphcd.dto.ReprogramarTurnoResponse;
import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Turno;
import com.fixsys.ctfyphcd.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteService pacienteService;

    public List<Turno> obtenerTurnosPorDni(String dni) {
        Paciente paciente = pacienteService.buscarPorDni(dni);
        if (paciente != null) {
            return turnoRepository.findByPaciente(paciente);
        }
        return null;
    }

    public CrearTurnoResponse crearNuevoTurno(String dniPaciente,
                                              LocalDate fechaTurno,
                                              String horario,
                                              String estado) {
        Paciente paciente = pacienteService.buscarPorDni(dniPaciente);
        if (paciente != null) {
            Turno nuevoTurno = new Turno();
            nuevoTurno.setPaciente(paciente);
            nuevoTurno.setFechaTurno(fechaTurno);
            nuevoTurno.setHorario(horario);
            nuevoTurno.setEstado("programado");
            turnoRepository.save(nuevoTurno);
            return new CrearTurnoResponse(dniPaciente, fechaTurno, horario, estado);
        }
        return null;
    }

    public ReprogramarTurnoResponse reprogramarTurno(Long turnoId,
                                                     String dniPaciente,
                                                     LocalDate nuevaFecha,
                                                     String horario,
                                                     String estado) {
        Turno turno = turnoRepository
                .findById(turnoId)
                .orElseThrow(()-> new RuntimeException("Turno no encontrado"));

        if (turno != null && dniPaciente != null && !dniPaciente.isEmpty()) {
            //SECCION DEPURACION
            System.out.println("DNI del paciente: " + dniPaciente);
            Paciente paciente = pacienteService.buscarPorDni(dniPaciente);
            if(paciente != null) {
                // seccion depuracion
                System.out.println("Paciente encontrado: " + paciente);
                turno.setPaciente(paciente);
                turno.setFechaTurno(nuevaFecha);
                turno.setHorario(horario);
                turno.setEstado("reprogramado");
                turnoRepository.save(turno);
                return new ReprogramarTurnoResponse(dniPaciente, nuevaFecha, horario, estado);
            } else {
                System.out.println("Paciente no encontrado para el DNI: " + dniPaciente);
            }
        } else {
            System.out.println("DNI del paciente es nulo o vacio");
        }
        return null;
    }

    public Turno cancelarTurno(Long turnoId) {
        Turno turno = turnoRepository
                .findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        if (turno != null) {
            turno.setEstado("cancelado");
            return turnoRepository.save(turno);
        }
        return null;
    }

}
