package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioRequestDTO(

        @Schema(description = "Nombre de usuario para login", example = "david123")
        String nombreUsuario,

        @Schema(description = "ID de la persona asociada al usuario",
                example = "1")
        Long personaId,

        @Schema(description = "ID del rol asignado al usuario",
                example = "2")
        Long rolId

) {
}