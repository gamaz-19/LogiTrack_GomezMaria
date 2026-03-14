package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.Detalle_movimientoRequestDTO;
import com.s1.LogiTrack.dto.response.Detalle_movimientoResponseDTO;

import java.util.List;

public interface Detalle_movimientoService {

    Detalle_movimientoResponseDTO guardarDetalleMovimiento(Detalle_movimientoRequestDTO dto);

    Detalle_movimientoResponseDTO actualizarDetalleMovimiento(Detalle_movimientoRequestDTO dto, Long id);

    List<Detalle_movimientoResponseDTO> buscarPorMovimiento(Long movimientoId);

    void eliminarDetalleMovimiento(Long id);

    List<Detalle_movimientoResponseDTO> listarDetalles();

    Detalle_movimientoResponseDTO buscarPorId(Long id);

}
