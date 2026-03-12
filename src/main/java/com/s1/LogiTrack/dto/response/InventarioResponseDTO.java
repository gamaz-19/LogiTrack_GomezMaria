package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record InventarioResponseDTO(

        @Schema(description = "ID del inventario", example = "1")
        Long id,

        @Schema(description = "Bodega donde se encuentra el producto")
        BodegaResponseDTO bodega,

        @Schema(description = "Producto almacenado en el inventario")
        ProductoResponseDTO producto,

        @Schema(description = "Cantidad de productos disponibles en el inventario", example = "150")
        Long stock

) {
}