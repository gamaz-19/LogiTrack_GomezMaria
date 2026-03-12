package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record Detalle_movimientoRequestDTO(

        @Schema(description = "ID del movimiento asociado",
                example = "1")
        Long movimientoId,

        @Schema(description = "ID del producto asociado",
                example = "5")
        Long productoId,

        @Schema(description = "Cantidad del producto en el movimiento",
                example = "10")
        Integer cantidad
) {
}