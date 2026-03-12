package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategoriaResponseDTO(

        @Schema(description = "ID de la categoria", example = "1")
        Long id,

        @Schema(description = "Nombre de la categoria", example = "Electronicos")
        String nombre

) {
}