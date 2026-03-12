package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record ProductoResponseDTO(

        @Schema(description = "ID del producto", example = "1")
        Long id,

        @Schema(description = "Nombre del producto", example = "Laptop")
        String nombre,

        @Schema(description = "Categoría a la que pertenece el producto")
        CategoriaResponseDTO categoria,

        @Schema(description = "Precio del producto", example = "1500.00")
        BigDecimal precio

) {
}