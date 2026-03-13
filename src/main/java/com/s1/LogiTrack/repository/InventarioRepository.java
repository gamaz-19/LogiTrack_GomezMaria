package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.Inventario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByBodegaId(Long bodegaId);

    List<Inventario> findByProductoId(Long productoId);

    Optional<Inventario> findByBodegaIdAndProductoId(Long bodegaId, Long productoId);
    //Optional - Evita errores de null, si no encuentra en ambos puede devler u empty()
    List<Inventario> findByStockLessThan(Long stock);

}