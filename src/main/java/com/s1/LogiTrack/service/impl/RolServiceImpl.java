package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.RolRequestDTO;
import com.s1.LogiTrack.dto.response.RolResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.RolMapper;
import com.s1.LogiTrack.model.Rol;
import com.s1.LogiTrack.repository.RolRepository;
import com.s1.LogiTrack.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Override
    public RolResponseDTO guardarRol(RolRequestDTO dto) {
        Rol nuevo = rolMapper.DTOAEntidad(dto);
        return rolMapper.entidadADTO(rolRepository.save(nuevo));
    }

    @Override
    public RolResponseDTO actualizarRol(RolRequestDTO dto, Long id) {
        Rol existente = rolRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Rol no encontrado con id: " + id));
        rolMapper.actualizarEntidadDesdeDTO(existente, dto);
        return rolMapper.entidadADTO(rolRepository.save(existente));
    }

    @Override
    public List<RolResponseDTO> buscarPorNombre(String nombre) {
        // RolRepository.findByNombre devuelve Optional, lo convertimos a lista
        return rolRepository.findByNombre(nombre)
                .stream()
                .map(rolMapper::entidadADTO)
                .toList();
    }

    @Override
    public void eliminarRol(Long id) {
        if (!rolRepository.existsById(id))
            throw new BusinessRuleException("Rol no encontrado con id: " + id);
        rolRepository.deleteById(id);
    }

    @Override
    public List<RolResponseDTO> listarRoles() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::entidadADTO)
                .toList();
    }

    @Override
    public RolResponseDTO buscarPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Rol no encontrado con id: " + id));
        return rolMapper.entidadADTO(rol);
    }
}