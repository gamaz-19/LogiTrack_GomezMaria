package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record Detalle_movimientoResponseDTO(

        @Schema(description = "ID del detalle del movimiento", example = "1")
        Long id,

        @Schema(description = "Movimiento asociado al detalle")
        MovimientoResponseDTO movimiento,

        @Schema(description = "Producto asociado al movimiento")
        ProductoResponseDTO producto,

        @Schema(description = "Cantidad de productos en el movimiento", example = "10")
        Integer cantidad

) {
}