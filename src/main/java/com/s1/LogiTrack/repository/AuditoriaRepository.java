package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.enums.TipoOperacion;
import com.s1.LogiTrack.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    List<Auditoria> findByUsuarioId(Long usuarioId);

    List<Auditoria> findByOperacion(TipoOperacion operacion);

    List<Auditoria> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}