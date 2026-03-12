package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategoriaRequestDTO(

        @Schema(description = "Nombre de la categoria",
                example = "Electronica")
        String nombre

) {
}
