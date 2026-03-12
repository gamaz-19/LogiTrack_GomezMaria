package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.enums.TipoMovimiento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record MovimientoResponseDTO(

        @Schema(description = "ID del movimiento", example = "1")
        Long id,

        @Schema(description = "Fecha y hora en que se realizo el movimiento")
        LocalDateTime fecha,

        @Schema(description = "Tipo de movimiento realizado", example = "ENTRADA")
        TipoMovimiento tipo,

        @Schema(description = "Usuario que realizo el movimiento")
        UsuarioResponseDTO usuario,

        @Schema(description = "Bodega de origen del movimiento")
        BodegaResponseDTO bodegaOrigen,

        @Schema(description = "Bodega de destino del movimiento")
        BodegaResponseDTO bodegaDestino

) {
}