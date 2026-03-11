package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record InventarioRequestDTO(

        Long bodegaId,

        Long productoId,

        @Schema(description = "Cantidad en stock del producto en la bodega",
                example = "100")
        Long stock

) {
}
