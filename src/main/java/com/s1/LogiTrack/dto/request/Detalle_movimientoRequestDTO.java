package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record Detalle_movimientoRequestDTO(

        Long movimientoId,

        Long productoId,

        @Schema(description = "Cantidad del producto en el movimiento",
                example = "10")
        Integer cantidad
) {
}
