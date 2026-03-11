package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioRequestDTO(

        @Schema(description = "Nombre de usuario para login", example = "david123")
        String nombreUsuario,

        Long personaId,

        Long rolId

) {
}
