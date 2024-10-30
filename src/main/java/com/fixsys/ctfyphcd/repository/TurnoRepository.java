package com.fixsys.ctfyphcd.repository;

import com.fixsys.ctfyphcd.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    List<Turno> findAllByDeletedFalse();
    Optional<Turno> findByIdAndDeletedFalse(Long id);
}
