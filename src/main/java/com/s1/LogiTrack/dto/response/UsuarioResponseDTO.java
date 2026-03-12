package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioResponseDTO(

        @Schema(description = "ID del usuario", example = "1")
        Long id,

        @Schema(description = "Nombre de usuario del sistema", example = "maria.gomez")
        String nombreUsuario,

        @Schema(description = "Información personal del usuario")
        PersonaResponseDTO persona,

        @Schema(description = "Rol asignado al usuario")
        RolResponseDTO rol

) {
}