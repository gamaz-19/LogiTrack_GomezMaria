package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.enums.TipoMovimiento;
import com.s1.LogiTrack.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Movimiento> findByUsuarioId(Long usuarioId);

    List<Movimiento> findByTipo(TipoMovimiento tipo);

}