package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record PersonaRequestDTO(

        @Schema(description = "Nombre de la persona", example = "David")
        String nombre,

        @Schema(description = "Apellido de la persona", example = "DM")
        String apellido,

        @Schema(description = "Documento de identidad", example = "1007999211")
        String documento,

        @Schema(description = "Numero de telefono", example = "3001234567")
        String telefono,

        @Schema(description = "Correo electronico", example = "david@example.com")
        String email,

        @Schema(description = "Direccion de residencia", example = "Calle 123 #45-67")
        String direccion

) {
}
