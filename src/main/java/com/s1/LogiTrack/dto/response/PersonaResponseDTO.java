package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record PersonaResponseDTO(

        @Schema(description = "ID de la persona", example = "1")
        Long id,

        @Schema(description = "Nombre de la persona", example = "Juan")
        String nombre,

        @Schema(description = "Apellido de la persona", example = "Pérez")
        String apellido,

        @Schema(description = "Número de documento de la persona", example = "123456789")
        String documento,

        @Schema(description = "Teléfono de contacto", example = "3001234567")
        String telefono,

        @Schema(description = "Correo electrónico de la persona", example = "juan.perez@email.com")
        String email,

        @Schema(description = "Dirección de residencia", example = "Calle 45 #12-34")
        String direccion

) {
}