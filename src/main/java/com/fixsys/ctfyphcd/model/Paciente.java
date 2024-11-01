package com.fixsys.ctfyphcd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String dni;

    private String birthdate;
    private String email;
    private String gender;
    private String address;
    private String phone;
    private String emergencyContactInformation;
    private String healthInsuranceNumber;

}
