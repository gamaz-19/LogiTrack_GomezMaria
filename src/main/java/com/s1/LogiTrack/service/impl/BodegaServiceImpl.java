package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.BodegaRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.BodegaMapper;
import com.s1.LogiTrack.mapper.PersonaMapper;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Persona;
import com.s1.LogiTrack.repository.BodegaRepository;
import com.s1.LogiTrack.repository.PersonaRepository;
import com.s1.LogiTrack.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;
    private final PersonaRepository personaRepository;
    private final BodegaMapper bodegaMapper;
    private final PersonaMapper personaMapper;

    private BodegaResponseDTO toDTO(Bodega b) {
        return bodegaMapper.entidadADTO(b, personaMapper.entidadADTO(b.getEncargado()));
    }

    @Override
    public BodegaResponseDTO guardarBodega(BodegaRequestDTO dto) {
        Persona encargado = personaRepository.findById(dto.encargadoId())
                .orElseThrow(() -> new BusinessRuleException("Persona no encontrada con id: " + dto.encargadoId()));
        Bodega nueva = bodegaMapper.DTOAEntidad(dto, encargado);
        return toDTO(bodegaRepository.save(nueva));
    }

    @Override
    public BodegaResponseDTO actualizarBodega(BodegaRequestDTO dto, Long id) {
        Bodega existente = bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Bodega no encontrada con id: " + id));
        Persona encargado = personaRepository.findById(dto.encargadoId())
                .orElseThrow(() -> new BusinessRuleException("Persona no encontrada con id: " + dto.encargadoId()));
        bodegaMapper.actualizarEntidadDesdeDTO(existente, dto, encargado);
        return toDTO(bodegaRepository.save(existente));
    }

    @Override
    public List<BodegaResponseDTO> buscarPorNombre(String nombre) {
        return bodegaRepository.findByNombre(nombre)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void eliminarBodega(Long id) {
        if (!bodegaRepository.existsById(id))
            throw new BusinessRuleException("Bodega no encontrada con id: " + id);
        bodegaRepository.deleteById(id);
    }

    @Override
    public List<BodegaResponseDTO> listarBodegas() {
        return bodegaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public BodegaResponseDTO buscarPorId(Long id) {
        Bodega b = bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Bodega no encontrada con id: " + id));
        return toDTO(b);
    }
}