package com.fixsys.ctfyphcd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Paciente paciente;

    private LocalDate fechaTurno;

    private String horario;

    private String estado; // "programado", "reprogramado", "cancelado"

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false; //Indica si el registro esta eliminado

    public String getPacienteDni() {
        return paciente != null ? paciente.getDni() : null;
    }
}
