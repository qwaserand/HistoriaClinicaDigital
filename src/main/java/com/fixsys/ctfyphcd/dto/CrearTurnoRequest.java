package com.fixsys.ctfyphcd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearTurnoRequest {
    private LocalDate fechaTurno;
    private String horario;
}
