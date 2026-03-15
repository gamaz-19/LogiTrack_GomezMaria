package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.PersonaResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.PersonaMapper;
import com.s1.LogiTrack.model.Persona;
import com.s1.LogiTrack.repository.PersonaRepository;
import com.s1.LogiTrack.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Override
    public PersonaResponseDTO guardarPersona(PersonaRequestDTO dto) {
        Persona nueva = personaMapper.DTOAEntidad(dto);
        return personaMapper.entidadADTO(personaRepository.save(nueva));
    }

    @Override
    public PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id) {
        Persona existente = personaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Persona no encontrada con id: " + id));
        personaMapper.actualizarEntidadDesdeDTO(existente, dto);
        return personaMapper.entidadADTO(personaRepository.save(existente));
    }

    @Override
    public List<PersonaResponseDTO> buscarPorNombre(String nombre) {
        // PersonaRepository no tiene findByNombre, filtramos con stream
        return personaRepository.findAll()
                .stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .map(personaMapper::entidadADTO)
                .toList();
    }

    @Override
    public void eliminarPersona(Long id) {
        if (!personaRepository.existsById(id))
            throw new BusinessRuleException("Persona no encontrada con id: " + id);
        personaRepository.deleteById(id);
    }

    @Override
    public List<PersonaResponseDTO> listarPersonas() {
        return personaRepository.findAll()
                .stream()
                .map(personaMapper::entidadADTO)
                .toList();
    }

    @Override
    public PersonaResponseDTO buscarPorId(Long id) {
        Persona p = personaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Persona no encontrada con id: " + id));
        return personaMapper.entidadADTO(p);
    }
}