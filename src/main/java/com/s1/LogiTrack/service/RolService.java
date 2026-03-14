package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.RolRequestDTO;
import com.s1.LogiTrack.dto.response.RolResponseDTO;

import java.util.List;

public interface RolService {

    RolResponseDTO guardarRol(RolRequestDTO dto);

    RolResponseDTO actualizarRol(RolRequestDTO dto, Long id);

    List<RolResponseDTO> buscarPorNombre(String nombre);

    void eliminarRol(Long id);

    List<RolResponseDTO> listarRoles();

    RolResponseDTO buscarPorId(Long id);

}
