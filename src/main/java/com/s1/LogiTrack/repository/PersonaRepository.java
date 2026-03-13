package com.s1.LogiTrack.repository;

import com.s1.LogiTrack.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByDocumento(String documento);

    Optional<Persona> findByEmail(String email);

}