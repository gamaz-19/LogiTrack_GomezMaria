package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    List<Bodega> findByNombre(String nombre);

    List<Bodega> findByUbicacion(String ubicacion);

}