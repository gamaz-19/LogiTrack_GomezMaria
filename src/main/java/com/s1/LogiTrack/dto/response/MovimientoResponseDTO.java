package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.model.TipoMovimiento;

import java.time.LocalDateTime;

public record MovimientoResponseDTO(

        Long id,
        LocalDateTime fecha,
        TipoMovimiento tipo,
        UsuarioResponseDTO usuario,
        BodegaResponseDTO bodegaOrigen,
        BodegaResponseDTO bodegaDestino

) {
}
