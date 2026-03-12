package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record InventarioRequestDTO(

        @Schema(description = "ID de la bodega donde se almacena el producto",
                example = "1")
        Long bodegaId,

        @Schema(description = "ID del producto almacenado en la bodega",
                example = "5")
        Long productoId,

        @Schema(description = "Cantidad en stock del producto en la bodega",
                example = "100")
        Long stock

) {
}