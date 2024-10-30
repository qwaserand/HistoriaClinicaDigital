package com.fixsys.ctfyphcd.service.impl;

import com.fixsys.ctfyphcd.dto.request.TurnoRequestDTO;
import com.fixsys.ctfyphcd.exceptions.custom.BadRequestException;
import com.fixsys.ctfyphcd.exceptions.custom.ResourceNotFoundException;
import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Turno;
import com.fixsys.ctfyphcd.repository.PacienteRepository;
import com.fixsys.ctfyphcd.repository.TurnoRepository;
import com.fixsys.ctfyphcd.service.TurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //------------------------------------------------------------------------------

    @Override
    public List<Turno> obtenerTurnos() {
        return turnoRepository.findAllByDeletedFalse();
    }

    @Override
    public Turno obtenerUnTurno(Long turnoId) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new BadRequestException("id invalido"));
        return null;
    }

    public Turno crearNuevoTurno(TurnoRequestDTO turnoRequestDTO) {

        Paciente paciente = pacienteRepository.findById(turnoRequestDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: "
                        + turnoRequestDTO.getPacienteId()));

        Turno turno = mapTurnoRequestDTOToTurno(turnoRequestDTO);

        turno.setPaciente(paciente);

        turno.setEstado("programado");

        turnoRepository.save(turno);
        return turno;
    }

    @Override
    public Turno reprogramarTurno(TurnoRequestDTO turnoRequestDTO, Long id) {

        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("id invalido"));
        turno.setFechaTurno(turnoRequestDTO.getFechaTurno());
        turno.setHorario(turnoRequestDTO.getHorario());

        turno.setEstado("reprogramado");

        turnoRepository.save(turno);
        return turno;
    }

    @Override
    public String cancelarTurno(Long turnoId) {

        Optional<Turno> optionalTurno = turnoRepository.findByIdAndDeletedFalse(turnoId);

        if (optionalTurno.isPresent()) {
            Turno turno = optionalTurno.get();

            if ("cancelado".equals(turno.getEstado())) {
                return "El turno ya está cancelado.";
            }

            turno.setEstado("cancelado");

            turnoRepository.save(turno);

            return "Turno cancelado exitosamente.";
        } else {
            return "No se encontró el turno con el ID  especificado.";
        }
    }

    @Override
    public void eliminarTurno(long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));
        //turnoRepository.delete(turno);

        turno.setDeleted(true);
        turnoRepository.save(turno);
    }

    private TurnoRequestDTO mapTurnoToTurnoRequestDTO(Turno turno) {
        TurnoRequestDTO turnoRequestDTO = modelMapper.map(turno, TurnoRequestDTO.class);
        return turnoRequestDTO;
    }

    private Turno mapTurnoRequestDTOToTurno(TurnoRequestDTO turnoRequestDTO) {
        Turno turno = modelMapper.map(turnoRequestDTO, Turno.class);
        return turno;
    }



}
