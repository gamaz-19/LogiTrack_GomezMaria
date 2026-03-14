package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.response.AuditoriaResponseDTO;
import com.s1.LogiTrack.enums.TipoOperacion;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaService {

    List<AuditoriaResponseDTO> listarAuditorias();

    AuditoriaResponseDTO buscarPorId(Long id);

    List<AuditoriaResponseDTO> buscarPorUsuario(Long usuarioId);

    List<AuditoriaResponseDTO> buscarPorOperacion(TipoOperacion operacion);

    List<AuditoriaResponseDTO> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
