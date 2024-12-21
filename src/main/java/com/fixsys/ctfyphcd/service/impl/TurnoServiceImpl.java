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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /*----------------------------------------------------------------------------*/

    @Override
    public List<Turno> obtenerTurnos() {
        return turnoRepository.findAllByDeletedFalse();
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public Turno obtenerUnTurno(Long turnoId) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new BadRequestException("id invalido"));

        return turno;
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public List<Turno> obtenerTurnosPorDniPaciente(String dni) {
        // Buscar el paciente por su DNI
        Paciente paciente = pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new BadRequestException("dni invalido"));

        // Buscar los turnos asociados a ese paciente
        return turnoRepository.findAllByPacienteAndDeletedFalse(paciente);
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public Turno crearNuevoTurno(TurnoRequestDTO turnoRequestDTO) {
        // Buscar el paciente por ID
        Paciente paciente = pacienteRepository.findById(turnoRequestDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: "
                        + turnoRequestDTO.getPacienteId()));

        // Crear manualmente el turno
        Turno turno = new Turno();
        turno.setFechaTurno(turnoRequestDTO.getFechaTurno());
        turno.setHorario(turnoRequestDTO.getHorario());
        turno.setEstado("programado");
        turno.setDeleted(false);
        turno.setPaciente(paciente); // Relación manual

        // Agregar el turno a la lista de turnos del paciente
        paciente.getTurnos().add(turno);

        // Guardar el paciente (propaga los cambios a los turnos debido al cascade)
        pacienteRepository.save(paciente);

        return turno;
    }

    /*----------------------------------------------------------------------------*/

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

    /*----------------------------------------------------------------------------*/

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

    /*----------------------------------------------------------------------------*/

    @Override
    public void eliminarTurno(long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));
        //turnoRepository.delete(turno);

        turno.setDeleted(true);
        turnoRepository.save(turno);
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public String guardarReceta(Long turnoId, MultipartFile archivo) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        // Guardar el archivo (puedes usar el sistema de archivos o una base de datos)
        Path rutaArchivo = Paths.get("recetas", turnoId + "_" + archivo.getOriginalFilename());
        try {
            Files.createDirectories(rutaArchivo.getParent());
            Files.write(rutaArchivo, archivo.getBytes());
            return "Archivo guardado en " + rutaArchivo.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
        }
    }

    /*----------------------------------------------------------------------------*/

    @Override
    public void deleteAllTurnos() {
        turnoRepository.deleteAll();
        turnoRepository.resetTurnoSequence();
    }

    /*----------------------------------------------------------------------------*/

    private TurnoRequestDTO mapTurnoToTurnoRequestDTO(Turno turno) {
        TurnoRequestDTO turnoRequestDTO = modelMapper.map(turno, TurnoRequestDTO.class);
        return turnoRequestDTO;
    }

    /*----------------------------------------------------------------------------*/

    private Turno mapTurnoRequestDTOToTurno(TurnoRequestDTO turnoRequestDTO) {
        Turno turno = modelMapper.map(turnoRequestDTO, Turno.class);
        return turno;
    }

    /*----------------------------------------------------------------------------*/

}
