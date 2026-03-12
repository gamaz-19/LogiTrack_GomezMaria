package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record RolResponseDTO(

        @Schema(description = "ID del rol", example = "1")
        Long id,

        @Schema(description = "Nombre del rol", example = "ADMIN")
        String nombre

) {
}