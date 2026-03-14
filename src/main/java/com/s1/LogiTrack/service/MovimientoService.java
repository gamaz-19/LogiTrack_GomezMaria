package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.enums.TipoMovimiento;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoService {

    MovimientoResponseDTO guardarMovimiento(MovimientoRequestDTO dto);

    MovimientoResponseDTO actualizarMovimiento(MovimientoRequestDTO dto, Long id);

    List<MovimientoResponseDTO> buscarPorTipo(TipoMovimiento tipo);

    List<MovimientoResponseDTO> buscarPorUsuario(Long usuarioId);

    List<MovimientoResponseDTO> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void eliminarMovimiento(Long id);

    List<MovimientoResponseDTO> listarMovimientos();

    MovimientoResponseDTO buscarPorId(Long id);

}
