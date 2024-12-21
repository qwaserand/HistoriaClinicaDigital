package com.fixsys.ctfyphcd.service;

import com.fixsys.ctfyphcd.model.Profesional;

import java.util.List;

public interface ProfesionalService {
    List<Profesional> getAllProfesionales();
    Profesional getProfesionalById(Long id);
    Profesional registerProfesional(Profesional profesional);
    void deleteProfesional(Long id);

}
