package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.TipoMovimiento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record MovimientoRequestDTO(

        @Schema(description = "Fecha en la que se realizó el movimiento",
                example = "2026-03-11T15:30:00")
        LocalDateTime fecha,

        TipoMovimiento tipo,

        Long usuarioId,

        Long bodegaOrigenId,

        Long bodegaDestinoId

) {
}
