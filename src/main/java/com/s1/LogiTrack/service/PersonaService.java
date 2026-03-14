package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.PersonaResponseDTO;

import java.util.List;

public interface PersonaService {

    PersonaResponseDTO guardarPersona(PersonaRequestDTO dto);

    PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id);

    List<PersonaResponseDTO> buscarPorNombre(String nombre);

    void eliminarPersona(Long id);

    List<PersonaResponseDTO> listarPersonas();

    PersonaResponseDTO buscarPorId(Long id);

}
