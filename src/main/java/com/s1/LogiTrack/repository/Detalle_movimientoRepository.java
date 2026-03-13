package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.Detalle_movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Detalle_movimientoRepository extends JpaRepository<Detalle_movimiento, Long> {

    List<Detalle_movimiento> findByMovimientoId(Long movimientoId);

    List<Detalle_movimiento> findByProductoId(Long productoId);

}