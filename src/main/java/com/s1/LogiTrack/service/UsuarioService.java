package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.UsuarioRequestDTO;
import com.s1.LogiTrack.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO dto);

    UsuarioResponseDTO actualizarUsuario(UsuarioRequestDTO dto, Long id);

    UsuarioResponseDTO buscarPorNombreUsuario(String nombreUsuario);

    List<UsuarioResponseDTO> buscarPorRol(Long rolId);

    void eliminarUsuario(Long id);

    List<UsuarioResponseDTO> listarUsuarios();

    UsuarioResponseDTO buscarPorId(Long id);

}
