package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;

import java.util.List;

public interface InventarioService {

    InventarioResponseDTO guardarInventario(InventarioRequestDTO dto);

    InventarioResponseDTO actualizarInventario(InventarioRequestDTO dto, Long id);

    List<InventarioResponseDTO> buscarPorBodega(Long bodegaId);

    List<InventarioResponseDTO> buscarPorProducto(Long productoId);

    void eliminarInventario(Long id);

    List<InventarioResponseDTO> listarInventarios();

    InventarioResponseDTO buscarPorId(Long id);

}
