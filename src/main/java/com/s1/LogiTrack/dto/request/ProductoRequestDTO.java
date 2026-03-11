package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductoRequestDTO(

        @Schema(description = "Nombre del producto", example = "Televisor 55\"")
        String nombre,

        Long categoriaId,

        @Schema(description = "Precio del producto", example = "1200.50")
        BigDecimal precio

) {
}
