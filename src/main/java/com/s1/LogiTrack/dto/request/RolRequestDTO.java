package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record RolRequestDTO(

        @Schema(description = "Nombre del rol", example = "ADMIN")
        String nombre

) {
}
