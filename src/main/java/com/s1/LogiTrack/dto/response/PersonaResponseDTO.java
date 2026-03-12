package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record PersonaResponseDTO(

        @Schema(description = "ID de la persona", example = "1")
        Long id,

        @Schema(description = "Nombre de la persona", example = "humpty")
        String nombre,

        @Schema(description = "Apellido de la persona", example = "dumpty")
        String apellido,

        @Schema(description = "Número de documento de la persona", example = "123456789")
        String documento,

        @Schema(description = "Telefono de contacto", example = "3001234567")
        String telefono,

        @Schema(description = "Correo electronico de la persona", example = "humpty.dumpty@email.com")
        String email,

        @Schema(description = "Direccion de residencia", example = "Calle 45 #12-34")
        String direccion

) {
}