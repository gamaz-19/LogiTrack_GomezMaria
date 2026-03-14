package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.CategoriaRequestDTO;
import com.s1.LogiTrack.dto.response.CategoriaResponseDTO;

import java.util.List;

public interface CategogriaService {

    CategoriaResponseDTO guardarCategoria(CategoriaRequestDTO dto);

    CategoriaResponseDTO actualizarCategoria(CategoriaRequestDTO dto, Long id);

    List<CategoriaResponseDTO> buscarPorNombre(String nombre);

    void eliminarCategoria(Long id);

    List<CategoriaResponseDTO> listarCategorias();

    CategoriaResponseDTO buscarPorId(Long id);

}
