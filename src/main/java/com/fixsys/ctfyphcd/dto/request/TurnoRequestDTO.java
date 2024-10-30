package com.fixsys.ctfyphcd.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoRequestDTO {
    private LocalDate fechaTurno;
    private String horario;
    private Long pacienteId;
}
