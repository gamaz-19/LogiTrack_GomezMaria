package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.TipoMovimiento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record MovimientoRequestDTO(

        @Schema(description = "Fecha en la que se realizó el movimiento")
        LocalDateTime fecha,

        @Schema(description = "Tipo de movimiento realizado",
                example = "ENTRADA")
        TipoMovimiento tipo,

        @Schema(description = "ID del usuario que realiza el movimiento",
                example = "1")
        Long usuarioId,

        @Schema(description = "ID de la bodega de origen del movimiento",
                example = "2")
        Long bodegaOrigenId,

        @Schema(description = "ID de la bodega de destino del movimiento",
                example = "3")
        Long bodegaDestinoId

) {
}