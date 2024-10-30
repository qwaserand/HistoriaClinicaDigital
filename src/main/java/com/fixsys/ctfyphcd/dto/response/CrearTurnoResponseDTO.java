package com.fixsys.ctfyphcd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearTurnoResponseDTO {
    private String dniPaciente;
    private LocalDate fechaTurno;
    private String horario;
    private String estado;
}
